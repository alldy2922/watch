package com.datn.watch.notification.service;//package com.pet.market.api.notification.service;
//
//import com.google.firebase.messaging.*;
//import com.pet.market.api.common.logging.APILogger;
//import com.pet.market.api.common.model.entity.PushMessage;
//import com.pet.market.api.common.utils.StringUtils;
//import com.pet.market.api.notification.common.Constant;
//import com.pet.market.api.notification.logic.RequestLogic;
//import com.pet.market.api.notification.model.dto.FirebaseDeviceDto;
//import com.pet.market.api.notification.model.entity.FirebaseDevice;
//import com.pet.market.api.notification.repository.RequestRepository;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.Duration;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
///**
// * @author tobi
// */
//@Service
//public class FirebaseService {
//    private static final APILogger logger = new APILogger(FirebaseService.class);
//
//    @Transactional
//    public void saveFirebaseDevice(FirebaseDeviceDto.Request request) {
//
//        String deviceToken = "";
//        Optional<FirebaseDevice> firebaseDeviceOptional = firebaseDeviceRepository
//                .findByCompanyCodeAndUserAccountAndDeviceToken(userCompanyCode, username, deviceToken);
//        FirebaseDevice firebaseDevice = new FirebaseDevice();
//        if (!firebaseDeviceOptional.isPresent()) {
//            firebaseDevice = new FirebaseDevice(firebaseDeviceRequest, username, companyCode, deviceToken);
//        } else {
//            firebaseDevice = firebaseDeviceOptional.get();
//            firebaseDevice.setFirebaseToken(firebaseDeviceRequest.getFirebaseToken());
//            firebaseDevice.setOs(firebaseDeviceRequest.getOs());
//            firebaseDevice.setCompanyCode(userCompanyCode);
//            firebaseDevice.setDeviceToken(deviceToken);
//        }
//        firebaseDevice = firebaseDeviceRepository.save(firebaseDevice);
//    }
//    @Transactional
//    public void sendFirebaseNotification(PushMessage req) {
////        Message message = buildMessageNotification(req, firebaseDevice.getFirebaseToken(),
////                (!StringUtils.isNullOrEmpty(request.getHeader(RequestHeaderConstants.DEVICE_ID))),
////                OS.ANDROID.equals(firebaseDevice.getOs()));
////
////        String response = sendAndGetResponse(message);
////        if (!StringUtils.isNullOrEmpty(response)) {
////            logger.logInfo(response);
////            logger.logInfo("+++++++++++++++++ SUCCESS push Firebase notification to user account: "
////                    + firebaseDevice.getUserAccount() + ", on device: " + firebaseDevice.getDeviceToken());
////        } else {
////            logger.logError("---------------- ERROR push Firebase notification to user account: "
////                    + firebaseDevice.getUserAccount() + ", on device: " + firebaseDevice.getDeviceToken());
////        }
//    }
//    private void subscribeToTopic(String token, String topic) {
//        FirebaseMessaging.getInstance().subscribeToTopicAsync(Arrays.asList(token), topic);
//    }
//
//    private void unSubscribeToTopic(String token, String topic) {
//        FirebaseMessaging.getInstance().unsubscribeFromTopicAsync(Arrays.asList(token), topic);
//    }
//
//    private String sendAndGetResponse(Message message) {
//        try {
//            return FirebaseMessaging.getInstance().send(message);
//        } catch (FirebaseMessagingException e) {
//            logger.logError("----------------- ERROR ----------------------   " + e.getMessage());
//            return null;
//        }
//    }
//    private Message buildMessageNotification(PushMessage request, String token, boolean isReceptionist,
//                                             boolean isAndroid) {
//        Message.Builder builder = Message.builder()
//                .setWebpushConfig(getWebpushConfig())
//                .setToken(token)
//                .putData(Constant.FIREBASE_TITLE, request.getSubject())
//                .putData(Constant.FIREBASE_BODY, request.getContent())
//                .putData(Constant.FIREBASE_NOTIFICATION_TYPE, request.getType().toString())
//                .putData(Constant.FIREBASE_TARGET_ID, request.getTargetId().toString());
//        if (!(isReceptionist && isAndroid)) {
//            builder.setNotification(Notification.builder().setTitle(request.getSubject())
//                    .setBody(request.getContent()).build());
//        }
//        return builder.build();
//
//    }
//
//    private ApnsConfig getApnsConfig() {
//        return ApnsConfig.builder().setAps(Aps.builder().setSound(Constant.FIREBASE_DEFAULT_SOUND).build()).build();
//    }
//    private WebpushConfig getWebpushConfig() {
//        return WebpushConfig.builder().setNotification(WebpushNotification.builder().setTitle("").build()).build();
//    }
//}
