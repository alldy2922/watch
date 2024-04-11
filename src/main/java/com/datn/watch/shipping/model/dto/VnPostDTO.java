package com.datn.watch.shipping.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.List;

public class VnPostDTO {

  @Data
  public static class VnPostOrder {

    private String orderContent;

    private Boolean international;

    private Data data;

    @Getter
    @Setter
    public static class Data {

      private Boolean international;

      private String orderContent;

      private Long senderId;

      /**
       * Thanh toan ngay: 2
       * Ghi nơ
       */
      private Integer methodPay;

      private String orgCodeAccept;

      private String senderPhone;

      private String senderName;

      private String senderAddress;

      private String senderProvinceCode;

      private String senderDistrictCode;

      private String senderCommuneCode;

      private String configPrintOrder;

      private String namePrinted;

      private String phonePrinted;

      private String addressPrinted;

      private String provinceCodePrinted;

      private String districtCodePrinted;

      private String communeCodePrinted;

      private String lon;

      private String lat;

      private String postCode;

      private String vpostCode;

      private String receiverCode;

      private Boolean isContractC;

      private String receiverContractNumber;

      private String receiverPhone;

      private String receiverName;

      private String receiverAddress;

      private String receiverProvinceCode;

      private String receiverDistrictCode;

      private String receiverCommuneCode;

      private String serviceCode;

      private List<Vas> vas;

      private List<String> contractServiceCodes;

      private String saleOrderCode;

      private Integer weight;

      private Integer length;

      private Integer width;

      private Integer height;

      private Integer dimWeight;

      private Integer priceWeight;

      private String contentNote;

      private Boolean isBroken;

      private List<OrderContent> orderContents;

      private String sendType;

      private String deliveryTime;

      private String deliveryInstruction;

      @Getter
      @Setter
      public static class Vas {

        private String vaCode;

        private List<Addon> addons;

        @Getter
        @Setter
        public static class Addon {

          private String vaCode;

          private String propCode;

          private String propValue;
        }

      }

      @Getter
      @Setter
      public static class OrderContent {

        private Integer itemContentId;

        private String nameVi;

        private Integer quantity;

        private Integer weight;

        private Long priceVnd;

        private String image;

      }
    }
  }

  @Data
  public static class VnPostFeeReq {

    private Long senderId;

    /**
     * Thanh toan ngay: 2
     * Ghi nơ
     */
    private Integer methodPay;

    private String orgCodeAccept;

    private String senderPhone;

    private String senderName;

    private String senderAddress;

    private String senderProvinceCode;

    private String senderDistrictCode;

    private String senderCommuneCode;

    private String configPrintOrder;

    private String namePrinted;

    private String phonePrinted;

    private String addressPrinted;

    private String provinceCodePrinted;

    private String districtCodePrinted;

    private String communeCodePrinted;

    private String lon;

    private String lat;

    private String postCode;

    private String vpostCode;

    private String receiverCode;

    private Boolean isContractC;

    private String receiverContractNumber;

    private String receiverPhone;

    private String receiverName;

    private String receiverAddress;

    private String receiverProvinceCode;

    private String receiverDistrictCode;

    private String receiverCommuneCode;

    private String serviceCode;

    private List<VnPostOrder.Data.Vas> vas;

    private List<String> contractServiceCodes;

    private String saleOrderCode;

    private Integer weight;

    private Integer length;

    private Integer width;

    private Integer height;

    private Integer dimWeight;

    private Integer priceWeight;

    private String contentNote;

    private Boolean isBroken;

    private List<VnPostOrder.Data.OrderContent> orderContents;

    private String sendType;

    private String deliveryTime;

    private String deliveryInstruction;

    private String senderContractNumber;

    private List<String> orderBillings;

    private List<String> orderImages;

  }

  @Data
  public static class VnPostFeeRes {

    private String calculation;

    private String calculateError;

    private Long totalFee;

    private Long mainFee;

    private Long vasFee;

    private Long vasFeeExtend;

    private Long codAmount;

    private String vehicle;


  }

  @Data
  public static class Spdv {

    private String code;

    private String name;

    public static Spdv toRes(com.pet.market.api.shipping.model.Spdv source) {
      Spdv target = new Spdv();
      BeanUtils.copyProperties(source, target);
      return target;
    }

  }

  @Data
  public static class Gtgt {

    private String code;

    private String name;

    private String spdv;

    private String type;

    public static Gtgt toRes(com.pet.market.api.shipping.model.Gtgt source) {
      Gtgt target = new Gtgt();
      BeanUtils.copyProperties(source, target);
      return target;
    }

  }

  @Data
  public static class Prop {

    private String code;

    private String name;

    private String gtgt;

    private String type;

    public static Prop toRes(com.pet.market.api.shipping.model.Prop source) {
      Prop target = new Prop();
      BeanUtils.copyProperties(source, target);
      return target;
    }

  }


}
