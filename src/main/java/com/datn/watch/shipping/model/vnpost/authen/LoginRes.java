package com.datn.watch.shipping.model.vnpost.authen;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRes {
    private Boolean success;
    private String errorMessage;
    private String token;
}
