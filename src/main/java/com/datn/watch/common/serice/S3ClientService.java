//package com.datn.watch.common.serice;
//
//import com.pet.market.api.common.exception.InvalidRequestException;
//import com.pet.market.api.common.exception.PetMarketApiException;
//import com.pet.market.api.common.model.entity.CmsObject;
//import com.pet.market.api.common.model.entity.CmsS3Object;
//import com.pet.market.api.common.utils.FileUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.stereotype.Service;
//import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
//import software.amazon.awssdk.core.sync.RequestBody;
//import software.amazon.awssdk.core.waiters.WaiterResponse;
//import software.amazon.awssdk.regions.Region;
//import software.amazon.awssdk.services.s3.S3Client;
//import software.amazon.awssdk.services.s3.S3Uri;
//import software.amazon.awssdk.services.s3.S3Utilities;
//import software.amazon.awssdk.services.s3.model.*;
//import software.amazon.awssdk.services.s3.presigner.S3Presigner;
//import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
//import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;
//import software.amazon.awssdk.services.s3.waiters.S3Waiter;
//
//import java.net.URI;
//import java.net.URL;
//import java.time.Duration;
//import java.util.*;
//import java.util.function.Consumer;
//
//@Service
//public class S3ClientService {
//
//    @Value("${app.object-storage.bucket}")
//    private String BUCKET_NAME;
//
//    private S3Client client;
//
//    private S3Presigner presigner;
//
//    public S3ClientService() {
//        ProfileCredentialsProvider profile = ProfileCredentialsProvider.create();
//        client = S3Client.builder().credentialsProvider(profile)
//                .region(Region.AP_NORTHEAST_2)
//                .forcePathStyle(true)
//                .build();
//
//        presigner = S3Presigner.builder()
//                .region(Region.AP_NORTHEAST_2)
//                .credentialsProvider(profile)
//                .build();
//    }
//
//    /**
//     * Create a bucket by using a S3Waiter object
//     *
//     * @param bucketName: Bucket name
//     * @param region:     Region
//     */
//    private void createBucket(String bucketName, Region region) {
//
//        S3Waiter s3Waiter = client.waiter();
//
//        try {
//            CreateBucketRequest bucketRequest = CreateBucketRequest.builder()
//                    .bucket(bucketName)
//                    .createBucketConfiguration(
//                            CreateBucketConfiguration.builder()
//                                    .locationConstraint(region.id())
//                                    .build())
//                    .build();
//
//            client.createBucket(bucketRequest);
//            HeadBucketRequest bucketRequestWait = HeadBucketRequest.builder()
//                    .bucket(bucketName)
//                    .build();
//
//            // Wait until the bucket is created and print out the response
//            WaiterResponse<HeadBucketResponse> waiterResponse = s3Waiter.waitUntilBucketExists(bucketRequestWait);
//            waiterResponse.matched().response().ifPresent(System.out::println);
//            System.out.println(bucketName + " is ready");
//
//        } catch (S3Exception e) {
//            System.err.println(e.awsErrorDetails().errorMessage());
//            System.exit(1);
//        }
//    }
//
//    /**
//     * List all objects in bucket
//     *
//     * @param bucketName Bucket name
//     * @return list of object
//     * @throws Exception
//     */
//    public List<CmsS3Object> listBucketObjects(String bucketName) throws Exception {
//
//        List<CmsS3Object> result = new ArrayList<>();
//
//        try {
//            ListObjectsRequest listObjects = ListObjectsRequest
//                    .builder()
//                    .bucket(bucketName)
//                    .build();
//
//            ListObjectsResponse res = client.listObjects(listObjects);
//            List<S3Object> objects = res.contents();
//            for (S3Object object : objects) {
//                if (object.size() != 0) { // Ignore empty object
//                    CmsS3Object cmsObject = new CmsS3Object(object.key(), getURL(bucketName, object.key()));
//                    result.add(cmsObject);
//                }
//            }
//
//            return result;
//
//        } catch (S3Exception e) {
//            throw new PetMarketApiException(e.getMessage());
//        }
//    }
//
//    /**
//     * Get Object url from S3
//     *
//     * @param bucketName Bucket name
//     * @param keyName    Object key
//     * @return AWS S3 url
//     */
//    public String getURL(String bucketName, String keyName) {
//
//        try {
//            GetUrlRequest request = GetUrlRequest.builder()
//                    .bucket(bucketName)
//                    .key(keyName)
//                    .build();
//
//            URL url = client.utilities().getUrl(request);
//            return url.toString();
//        } catch (S3Exception e) {
//            throw new InvalidRequestException(e.awsErrorDetails().errorMessage());
//        }
//
//    }
//
//
//    public String getPresigednUrl(String bucketName, String keyName) {
//
//        try {
//            GetUrlRequest request = GetUrlRequest.builder()
//                    .bucket(bucketName)
//                    .key(keyName)
//                    .build();
//
//            URL url = client.utilities().getUrl(request);
//            return url.toString();
//        } catch (S3Exception e) {
//            throw new InvalidRequestException(e.awsErrorDetails().errorMessage());
//        }
//
//    }
//
//    /**
//     * Put Object to AWS S3
//     *
//     * @param bucketName: Bucket name
//     * @param objectKey   Upload Object key
//     * @param data        Data of object
//     * @return key and object url in S3.
//     */
//    public CmsS3Object putObject(String bucketName, String objectKey, byte[] data) {
//        try {
//            Map<String, String> metadata = new HashMap<>();
//            Optional<String> ext = FileUtils.getExt(objectKey);
//            if (ext.isEmpty()) {
//                throw new InvalidRequestException("입력파일을 확인해주세요!");
//            }
//
//            String contentType = FileUtils.convertContentType(ext.get());
//            if (StringUtils.isEmpty(contentType)) {
//                throw new InvalidRequestException("입력 파일 형식을 확인하세요. 파일 형식만 지원: " + FileUtils.displayContentTypes());
//            }
//
//            PutObjectRequest putOb = PutObjectRequest.builder()
//                    .bucket(bucketName)
//                    .key(objectKey)
//                    .metadata(metadata)
////                    .acl("public-read")
//                    .contentType(contentType)
//                    .build();
//
//            client.putObject(putOb, RequestBody.fromBytes(data));
//
//            return new CmsS3Object(objectKey, getURL(bucketName, objectKey));
//        } catch (S3Exception e) {
//            throw new PetMarketApiException(e.getMessage());
//        }
//    }
//
//    public boolean putMultiObjects(String bucketName, List<CmsObject> sources) {
//
//        List<PutObjectRequest> requests = new ArrayList<>();
//
//        Consumer<CmsObject> handleObject = item ->
//        {
//            PutObjectRequest request = PutObjectRequest.builder()
//                    .bucket(bucketName)
//                    .key(item.getKey())
//                    .acl("public-read")
//                    .contentType(FileUtils.getExt(item.getExt()).get())
//                    .build();
//            requests.add(request);
//        };
//
//        sources.forEach(handleObject);
//
//        return true;
//    }
//
//    /**
//     * Delete objct with url
//     *
//     * @param url
//     * @param url
//     */
//    public void deleteObjectByUrl(String url) {
//
//        if (StringUtils.isBlank(url)) return;
//
//        S3Utilities s3Utilities = client.utilities();
//        URI uri = URI.create(url);
//        S3Uri s3Uri = s3Utilities.parseUri(uri);
//
//        DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
//                .bucket(s3Uri.key().orElse(null))
//                .key(s3Uri.key().orElse(null))
//                .build();
//
//        client.deleteObject(deleteObjectRequest);
//    }
//
//    /**
//     * Delete object by key
//     * @param key Object key
//     */
//    public void deleteObject(String bucket, String key) {
//
//        if (StringUtils.isBlank(key)) return;
//
//
//        DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
//                .bucket(bucket)
//                .key(key)
//                .build();
//
//        client.deleteObject(deleteObjectRequest);
//    }
//
//    @Async
//    public void deleteMultiObjects(List<String> urlList) {
//        S3Utilities s3Utilities = client.utilities();
//
//        Set<ObjectIdentifier> keys = new HashSet<>();
//        ObjectIdentifier objectId;
//
//        for (String url : urlList) {
//            URI uri = URI.create(url);
//            S3Uri s3Uri = s3Utilities.parseUri(uri);
//
//            objectId = ObjectIdentifier.builder()
//                    .key(s3Uri.key().orElse(null))
//                    .build();
//
//            keys.add(objectId);
//        }
//
//        // Delete multiple objects in one request.
//        Delete del = Delete.builder()
//                .objects(keys)
//                .build();
//        try {
//
//            DeleteObjectsRequest deleteObjectRequest = DeleteObjectsRequest.builder()
//                    .bucket(BUCKET_NAME)
//                    .delete(del)
//                    .build();
//
//            client.deleteObjects(deleteObjectRequest);
//
//        } catch (S3Exception e) {
//            System.err.println(e.awsErrorDetails().errorMessage());
//            System.exit(1);
//        }
//    }
//
//    /**
//     * Get presigned Url from S3
//     * @param bucketName
//     * @param key
//     * @return
//     */
//    public String generatePresignedUrl(String bucketName, String key) {
//
//        try {
//            GetObjectRequest getObjectRequest = GetObjectRequest.builder()
//                    .bucket(bucketName)
//                    .key(key)
//                    .build();
//
//            GetObjectPresignRequest getObjectPresignRequest = GetObjectPresignRequest.builder()
//                    .signatureDuration(Duration.ofMinutes(30))
//                    .getObjectRequest(getObjectRequest)
//                    .build();
//
//            PresignedGetObjectRequest presignedGetObjectRequest = presigner.presignGetObject(getObjectPresignRequest);
//            String theUrl = presignedGetObjectRequest.url().toString();
//            System.out.println("Presigned URL: " + theUrl);
//            return theUrl;
//        } catch (S3Exception  e) {
//            e.getStackTrace();
//            return null;
//        }
//    }
//
//}
