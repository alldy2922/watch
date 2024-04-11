//package com.datn.watch.common.config;
//
//import com.google.firebase.database.DatabaseReference;
//import com.pet.market.api.common.logging.APILogger;
//import jakarta.annotation.PostConstruct;
//import lombok.Getter;
//import lombok.Setter;
//import lombok.extern.log4j.Log4j2;
//import org.modelmapper.ModelMapper;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.stereotype.Component;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.concurrent.ExecutionException;
//
//@Getter
//@Setter
//@Component
//@ConfigurationProperties(prefix = "app.notification.firebase")
//@Log4j2
//public class FCMConfig {
//
//    private static final APILogger logger = new APILogger(FCMConfig.class);
//    private String firebaseConfigFile = "firebase.json";
//    private DatabaseReference databaseReference;
//    private ModelMapper modelMapper;
//
//    @PostConstruct
//    private void initializeFirebase() {
////        try {
////            logger.logInfo("---------------------------------");
////            logger.logInfo("Initializing FIREBASE from: " + firebaseConfigFile);
////            logger.logInfo("---------------------------------");
////
////            // Load Firebase configuration from the classpath
////            ClassPathResource classPathResource = new ClassPathResource(firebaseConfigFile);
////            InputStream firebaseCfgInput = classPathResource.getInputStream();
////
////            // Build FirebaseOptions using the loaded credentials
////            FirebaseOptions options = FirebaseOptions.builder()
////                    .setCredentials(GoogleCredentials.fromStream(firebaseCfgInput))
////                    .setDatabaseUrl("https://pet-market-93c3f-default-rtdb.asia-southeast1.firebasedatabase.app")
////                    .build();
////
////            firebaseCfgInput.close();
////
////            // Initialize FirebaseApp if no apps exist
////            if (FirebaseApp.getApps().isEmpty()) {
////                FirebaseApp.initializeApp(options);
////                databaseReference = FirebaseDatabase.getInstance().getReference();
////            } else {
////                logger.logInfo("FirebaseApp is already initialized.");
////            }
////        } catch (IOException e) {
////            logger.logError("Error initializing FirebaseApp", e);
////        }
//    }
//
//    public void pushDataToFirebase(String key, Map<String, Object> data) {
//        try {
//            DatabaseReference notificationRef = databaseReference.child(key);
//            notificationRef.updateChildrenAsync(data).get();
//        } catch (InterruptedException | ExecutionException e) {
//            logger.logError("Error initializing FirebaseApp", e);
//        }
//    }
//
//    public void updateFieldInFirebase(String nodePath, String childKey, String fieldToUpdate, Object newValue) {
//        DatabaseReference childReference = databaseReference.child(nodePath + "/" + childKey);
//        Map<String, Object> updateField = new HashMap<>();
//        updateField.put(fieldToUpdate, newValue);
//        childReference.updateChildrenAsync(updateField);
//    }
//}
