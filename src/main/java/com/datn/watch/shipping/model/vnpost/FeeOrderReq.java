package com.datn.watch.shipping.model.vnpost;

import com.pet.market.api.interlock.shipping.model.vnpost.OrderCreationReq.InformationOrder.AddonService;
import com.pet.market.api.interlock.shipping.model.vnpost.OrderCreationRes.AdditionRequest;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class FeeOrderReq {

  private Integer scope;

  @Size(max = 20)
  private String customerCode;

  @Size(max = 100)
  private String contractCode;

  private Data data;

  @Getter
  @Setter
  public static class Data {

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

    @Max(255)
    @NotBlank
    private String receiverAddress;

    @Max(5)
    private String receiverProvinceCode;

    @Max(255)
    private String receiverProvinceName;

    @Max(5)
    private String receiverDistrictCode;

    @Max(255)
    private String receiverDistrictName;

    @Max(5)
    private String receiverCommuneCode;

    @Max(255)
    private String receiverCommuneName;

    @Max(10)
    @NotBlank
    private String receiverNational;

    @Max(50)
    private String receiverCity;

    @Max(10)
    private String orgCodeAccept;

    @Max(6)
    private String receiverPostCode;

    private Integer weight;

    private Integer width;

    private Integer length;

    private Integer height;

    @Max(10)
    private String serviceCode;

    private List<AddonService> addonService;

    private List<AdditionRequest> additionRequest;

    @Max(5)
    private String vehicle;

  }
}
