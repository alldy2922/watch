package com.datn.watch.shipping.model.vnpost;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FeeOrderRes {

  private String serviceCode;

  private String serviceName;

  private Integer weightConvert;

  private Integer priceWeight;

  private Integer mainFee;

  private Integer vasfee;

  private Integer totalFee;

  private List<AddOnService> addonService;

  private List<AddRequest> additionRequest;

  @Getter
  @Setter
  public static class AddOnService {

    private String code;

    private String name;

    private Integer fee;

  }

  @Getter
  @Setter
  public static class AddRequest {

    private String code;

    private String name;

    private Integer fee;

  }
}
