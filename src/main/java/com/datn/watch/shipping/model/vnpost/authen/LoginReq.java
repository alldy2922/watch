package com.datn.watch.shipping.model.vnpost.authen;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginReq {
    private String username;
    private String password;
    private String customerCode;
}
