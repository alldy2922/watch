package com.datn.watch.customer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerAreaDto extends CustomerDto {
    private String provinceName;
    private String districtName;
    private String communeName;

}
