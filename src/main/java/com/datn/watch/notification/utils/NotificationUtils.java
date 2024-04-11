package com.datn.watch.notification.utils;

import com.pet.market.api.common.config.FCMConfig;
import com.pet.market.api.common.constant.UserConstant;
import com.pet.market.api.common.logging.APILogger;
import com.pet.market.api.common.utils.AuthUtils;
import com.pet.market.api.salespromotion.flashsale.controller.FlashSaleRestController;
import com.pet.market.api.user.account.utils.UserUtils;
import com.pet.market.api.notification.model.entity.Notification;
import com.pet.market.api.notification.repository.NotificationRepository;
import com.pet.market.api.request.model.entity.Request;
import com.pet.market.api.user.account.modal.entity.User;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author mttai
 * @date 8/1/2024
 * @desc Utility class for handling notifications and interacting with Firebase Realtime Database.
 * This class provides methods to push data to Firebase Realtime Database using Firebase Cloud Messaging (FCM).
 */
@Component
public class NotificationUtils {
    private static NotificationRepository mapper;
    private static final APILogger logger = new APILogger(NotificationUtils.class);

    /**
     * Firebase Cloud Messaging configuration.
     */
    private static FCMConfig fcmConfig;

    /**
     * Constructor to initialize the NotificationUtils with FCM configuration.
     *
     * @param fcmConfig The FCM configuration to be used for sending notifications.
     */
    public NotificationUtils(FCMConfig fcmConfig, NotificationRepository mapper) {
        NotificationUtils.mapper = mapper;
        NotificationUtils.fcmConfig = fcmConfig;
    }

    /**
     * Pushes data to Firebase Realtime Database using the provided key and object.
     * Create new data or update by key
     *
     * @param key    The key under which the data will be stored in the Firebase Realtime Database.
     * @param object The object to be stored in the Firebase Realtime Database.
     * @throws IllegalArgumentException If the key is null or empty.
     */
    public static void pushDataToFRDB(@NotNull String key, Map<String, Object> object) {
        if (key == null || key.isEmpty()) {
            throw new IllegalArgumentException("Key cannot be null or empty.");
        }
        // Utilize the FCMConfig instance to push data to Firebase Realtime Database.
        fcmConfig.pushDataToFirebase(key, object);
    }

    public static void markMyNotificationRead(@NotNull String key, Long[] ids) {
        for (Long id : ids) {
            fcmConfig.updateFieldInFirebase(key, id + "", "isRead", true);
        }
    }

    public static void createNotification(Request request) {
        try {
            List<User> users = UserUtils.getAllUserByRole(UserConstant.USER_ROLE_ADMIN);
            users.forEach(user -> {
                Notification notification = request.toNotification(user.getUsername());
                mapper.save(notification);
                NotificationUtils.pushDataToFRDB(AuthUtils.getUserId(), notification.convertToObjectOfFRDB());
            });
        }catch (Exception exception){
            logger.logError("NotificationUtils.createNotification : "+exception.getMessage());
        }
    }
}
