package com.datn.watch.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateUtils {

  public static boolean validateVietNamePhone(String phone) {
    String regex = "^(84|0[0-9])+([0-9]{8})\\b$";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(phone);
    return matcher.matches();
  }

}
