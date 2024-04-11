package com.datn.watch.uploadingfiles.client;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.pet.market.api.common.exception.S3Exception;
import com.pet.market.api.common.utils.StringUtils;
import jakarta.annotation.PostConstruct;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.Objects;

/**
 * @author tobi
 */
@Component
@Log4j2
public class S3Client {
    @Value("${app.s3Properties.endpointUrl}")
    private String endpointUrl;
    @Value("${app.s3Properties.bucketName}")
    private String bucketName;
    @Value("${app.s3Properties.accessKey}")
    private String accessKey;
    @Value("${app.s3Properties.secretKey}")
    private String secretKey;
    private AmazonS3 s3Client;

    @Value("${app.s3Properties.region}")
    private String region;

    @Value("${app.s3Properties.signingRegion}")
    private String signingRegion;

    @Value("${app.s3Properties.publicUrl}")
    private String publicUrl;

    @Value("${app.s3Properties.enabled}")
    private boolean enabled;

    @PostConstruct
    private void initializeS3() {
        if (enabled) {
            log.info("---------------------------------");
            log.info("Initializing Connect CloudFlare bucket name: " + bucketName);
            log.info("---------------------------------");
            AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
            this.s3Client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials)).withEndpointConfiguration(
                    new AwsClientBuilder.EndpointConfiguration(endpointUrl, signingRegion)).build();
        }
    }

    public String uploadFile(String folder, MultipartFile multipartFile) {
        File file = convertMultipartToFile(multipartFile);
        String fileName = (StringUtils.isNullOrEmpty(folder) ? "" : (folder + "/")) + generateFileName(multipartFile);
        return uploadFileToS3Bucket(bucketName, fileName, file);
    }

    private String uploadFileToS3Bucket(String bucketName, String fileName, File file) {
        try {
            if (enabled) {
                s3Client.putObject(
                        new PutObjectRequest(bucketName, fileName, file).withCannedAcl(CannedAccessControlList.PublicRead));
                log.info("Public Url image to S3: " + publicUrl + fileName);
                return publicUrl + fileName;
            } else {
                throw new S3Exception(S3Exception.CLOUDFLARE_DISABLED);
            }
        } catch (Exception e) {
            throw new S3Exception(S3Exception.PUSH_CLOUDFLARE_FAIL);
        }

    }

    @SneakyThrows
    private File convertMultipartToFile(MultipartFile file) {
        File convertedFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(convertedFile);
        fos.write(file.getBytes());
        fos.close();
        return convertedFile;
    }

    private String generateFileName(MultipartFile multiPart) {
        return new Date().getTime() + "-" + Objects.requireNonNull(multiPart.getOriginalFilename()).replace(" ", "_");
    }
}
