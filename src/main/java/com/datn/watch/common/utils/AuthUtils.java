package com.datn.watch.common.utils;

import com.datn.watch.common.Constant;

public class AuthUtils {

    public static boolean isLoggedUser() {
//        return SecurityUtils.isAuthenticated();
        return false;
    }

    public static String getUserId() {
//        Optional<String> username = SecurityUtils.getCurrentUserLogin();
//        return username.isPresent() ? username.get() : Constant.AUTH_ANONYMOUS_USER;
        return  Constant.AUTH_ANONYMOUS_USER;
    }
}
