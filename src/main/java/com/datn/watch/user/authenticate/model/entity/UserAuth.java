package com.datn.watch.user.authenticate.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserAuth{
    protected Long id;
    protected String username;
    protected String password;
    protected String name;
    protected List<RoleAuth> roles;
}
