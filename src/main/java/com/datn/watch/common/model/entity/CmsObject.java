package com.datn.watch.common.model.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CmsObject extends CmsS3Object {

    private String ext;
    public CmsObject(String key, String url, String ext) {
        super(key, url);
        this.ext = ext;
    }

}
