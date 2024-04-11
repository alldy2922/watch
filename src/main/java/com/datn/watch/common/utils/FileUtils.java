//package com.datn.watch.common.utils;
//
//import org.springframework.util.StringUtils;
//
//import java.time.Instant;
//import java.util.Base64;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Optional;
//
//public class FileUtils {
//    private static Map<String, String> contentTypes = new HashMap<>();
//
//    static {
//        contentTypes.put("png", "image/png");
//        contentTypes.put("jpg", "image/jpeg");
//        contentTypes.put("jpeg", "image/jpeg");
//        contentTypes.put("gif", "image/gif");
//        contentTypes.put("mp4", "video/mp4");
//    }
//
//    public static String convertContentType(String ext) {
//        return contentTypes.get(ext);
//    }
//
//    public static Optional<String> getExt(String filename) {
//        return Optional.ofNullable(filename)
//                .filter(f -> f.contains("."))
//                .map(f -> f.substring(filename.lastIndexOf(".") + 1));
//    }
//
//    public static String displayContentTypes() {
//        return contentTypes.keySet().toString();
//    }
//
//    public static byte[] convertBase64(String source) {
//        if (!StringUtils.hasText(source)) {
//            throw new InvalidRequestException("validation.source");
//        }
//        return Base64.getDecoder().decode(source);
//    }
//
//    public static String randomFileName(String originalName) {
//        return Instant.now().toEpochMilli() + originalName;
//    }
//
//}
