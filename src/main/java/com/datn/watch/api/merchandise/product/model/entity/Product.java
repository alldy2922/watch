package com.datn.watch.api.merchandise.product.model.entity;


import com.datn.watch.common.model.entity.AuditableEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author tobi
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Product extends AuditableEntity {
    private Long id;
    private String name;
    private Long price;
    private String thumbnail;
    private String thumbnailPc;
    private String thumbnailMo;
    private String image;
    private String unit;
    private String weight;
    private String info;
    private String preferential;
    private String status;
    private String idCategory;
    private String idBrand;
    private Integer amount;
    private String attributes;
    private String htmlImageData;
    private String htmlAttributesData;
    private String shoppeLink;
    private String tiktokLink;
    private String qrCode;
    private Integer sort;
    private String isNew;
    private String isBestSale;
    private String isStop;

}

