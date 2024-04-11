package com.datn.watch.user.account.utils;

import com.pet.market.api.user.account.modal.entity.User;
import com.pet.market.api.user.account.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Utility class for interacting with user-related functionalities.
 * This class provides methods to retrieve user data from the repository based on roles.
 */
@Component
public class UserUtils {

    /**
     * UserRepository for accessing user data.
     */
    private static UserRepository userRepository;

    /**
     * Constructor to initialize the UserUtils with the UserRepository.
     *
     * @param userRepository The repository providing access to user data.
     */
    public UserUtils(UserRepository userRepository) {
        UserUtils.userRepository = userRepository;
    }

    /**
     * Retrieves a list of users based on their role.
     *
     * @param role The role of users to retrieve.
     * @return A list of users with the specified role.
     */
    public static List<User> getAllUserByRole(String role) {
        // Utilize the UserRepository instance to retrieve users by role.
        return userRepository.findUserByRole(role);
    }
}
