package com.datn.watch.common.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CmsS3Object {

    private String key;

    private String url;
}
