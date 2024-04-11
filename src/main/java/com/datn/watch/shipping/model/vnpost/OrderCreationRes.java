package com.datn.watch.shipping.model.vnpost;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderCreationRes {

  private String orderHdrID;

  private String originalID;

  private String itemCode;

  private String originalItemCode;

  private Integer status;

  private String saleOrderCode;

  private String senderCode;

  private String senderContractNumber;

  private String senderName;

  private String senderPhone;

  private String senderEmail;

  private String senderAddress;

  private String senderProvinceCode;

  private String senderProvinceName;

  private String senderDistrictCode;

  private String senderDistrictName;

  private String senderCommuneCode;

  private String senderCommuneName;

  private String senderPostcode;

  private String receiverCode;

  private String receiverName;

  private String receiverContractNumber;

  private String receiverAddress;

  private String receiverProvinceCode;

  private String receiverProvinceName;

  private String receiverDistrictCode;

  private String receiverDistrictName;

  private String receiverCommCode;

  private String receiverCommName;

  private String receiverPostcode;

  private String receiverPhone;

  private String receiverEmail;

  private String serviceCode;

  private Long totalFee;

  private Long mainFee;

  private Long mainFeeBeforeTax;

  private Long mainTax;

  private Long vasFee;

  private Long codAmount;

  private List<AddonService> addonService;

  private List<AdditionRequest> additionRequest;

  private Integer weight;

  private Integer length;

  private Integer width;

  private Integer height;

  private Integer dimWeight;

  private Long priceWeight;

  private String deliveryInstruction;

  private Integer quantity;

  private String contentNote;

  private List<ContentDetail> contentDetail;

  private String sendType;

  private String isBroken;

  private String deliveryTime;

  private String deliveryRequire;

  private String vehicle;

  private String awbNumber;

  private String voucher;

  private String orgCodeCollect;

  private String orgCodeAccept;

  private String createdBy;

  private String createdDate;

  private String updateBy;

  private String updateDate;

  private String acceptedDate;

  private String sortingCode;

  private String paymentStatus;

  private Integer caseID;

  private Integer caseTypeName;

  private Integer caseStatus;

  private String source;

  private String inputType;

  private String inputMethod;

  private List<Document> documents;

  private List<PackageInfo> packageInfo;

  @Getter
  @Setter
  public static class AddonService {
    private String code;

    private Long fee;

    private Long feeBeforeTax;

    private Long tax;

    private Float taxRate;

    private List<PropList> propList;

  }

  @Getter
  @Setter
  public static class AdditionRequest {

    private String code;

    private Long fee;

    private Long feeBeforeTax;

    private Long tax;

    private Float taxRate;

    private List<PropList> propList;

  }

  @Getter
  @Setter
  public static class PropList {

    private String propCode;

    private String propValue;

    private String propValueActual;
  }

  @Getter
  @Setter
  public static class ContentDetail {

    private String nameVi;

    private String nameEn;

    private String hsCode;

    private Integer quantity;

    private Integer weight;

    private Integer netWeight;

    private Long amountVND;

    private String originProduct;

    private Long amountUSD;

  }

  @Getter
  @Setter
  public static class Document {

    private String docType;

    private String docNumber;

  }

  @Getter
  @Setter
  public static class PackageInfo {

    private String packageNo;

    private Integer weight;

    private Integer length;

    private Integer width;

    private Integer height;

    private String isUsePalet;

  }

}
