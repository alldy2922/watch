package com.datn.watch.user.authenticate.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RoleAuth implements GrantedAuthority {
    private String role;
    @Override
    public String getAuthority() {
        return this.role;
    }
}
