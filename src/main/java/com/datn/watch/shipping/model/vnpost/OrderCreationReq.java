package com.datn.watch.shipping.model.vnpost;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderCreationReq {

  private Integer orderCreationStatus;

  @Size(max = 255)
  private String type;

  @Size(max = 255)
  private String customerCode;

  @Size(max = 255)
  private String contractCode;

  private InformationOrder informationOrder;

  @Getter
  @Setter
  public static class InformationOrder {

    @Max(255)
    @NotBlank
    private String senderName;

    @Max(50)
    @NotBlank
    private String senderPhone;

    private String senderEmail;

    @Max(500)
    private String senderAddress;

    @Max(5)
    private String senderProvinceCode;

    @Max(5)
    private String senderProvinceName;

    @Max(5)
    private String senderDistrictName;

    @Max(5)
    private String senderDistrictCode;

    @Max(5)
    private String senderCommuneCode;

    @Max(5)
    private String senderCommuneName;

    @Max(255)
    @NotBlank
    private String receiverName;

    @Max(500)
    @NotBlank
    private String receiverAddress;

    @Max(255)
    private String receiverProvinceName;

    @Max(5)
    private String receiverDistrictCode;

    @Max(255)
    private String receiverDistrictName;

    @Max(5)
    private String receiverCommuneCode;

    //@Max(255)
    private String receiverCommuneName;

    @Max(50)
    private String receiverPhone;

    @Max(50)
    private String receiverEmail;

    @Max(10)
    @NotBlank
    private String serviceCode;

    private List<AddonService> addonService;

    private List<AddonServiceReq> additionRequest;

    @Max(500)
    private String propValue;

    @Max(20)
    private String orgCodeCollect;

    @Max(50)
    private String orgCodeAccept;

    @Max(50)
    private String saleOrderCode;

    @Max(377)
    @NotBlank
    private String contentNote;

    @NotNull
    private Integer weight;

    @NotNull
    private Integer width;

    private Integer length;

    private Integer height;

    @Max(10)
    @NotBlank
    private String vehicle;

    @Max(10)
    @NotBlank
    private String sendType;

    @Max(1)
    @NotBlank
    private String isBroken;

    @Max(20)
    private String deliveryTime;

    @Max(20)
    private String deliveryRequire;

    @Max(377)
    private String deliveryInstruction;

    @Getter
    @Setter
    public static class AddonService {

      @Max(10)
      private String code;

      @Max(500)
      private String propValue;

    }

    @Getter
    @Setter
    public static class AddonServiceReq {

      @Max(10)
      private String code;

      @Max(500)
      private String propValue;

    }

  }

}
