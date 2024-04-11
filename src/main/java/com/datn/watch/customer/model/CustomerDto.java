package com.datn.watch.customer.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    private String id;
    @NotBlank
    private String fullName;
    @Pattern(regexp="\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}")
    private String phoneNumber;
    @NotBlank
    private String province;
    @NotBlank
    private String district;
    @NotBlank
    private String commune;
    @NotBlank
    private String address;
    private Integer gender;
    private String birthDay;
    private Integer type;
    private String taxCode;
    private String email;
    private String facebook;
    private String customerGroup;
    private String note;
    private Integer status;

    public CustomerDto(String fullName, String phoneNumber, String province, String district, String commune, String address) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.province = province;
        this.district = district;
        this.commune = commune;
        this.address = address;
    }
}
