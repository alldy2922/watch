package com.datn.watch.shipping.model.vnpost;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CancelOrderReq {
    @NotBlank
    @Size(min = 1, max = 50)
    private String originalId;
}
