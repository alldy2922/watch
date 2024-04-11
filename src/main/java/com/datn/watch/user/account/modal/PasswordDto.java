package com.datn.watch.user.account.modal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PasswordDto {
    @NotBlank
    @Size(min = 4, max = 100)
    private String oldPassword;
    @NotBlank
    @Size(min = 4, max = 100)
    private String newPassword;
}
