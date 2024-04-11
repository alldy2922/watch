package com.datn.watch.shipping.model.vnpost;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class UpdateOrderReq {

  @Max(50)
  @NotBlank
  private String OriginalId;

  @Max(6)
  @NotBlank
  private String ServiceCode;

  @Max(13)
  @NotBlank
  private String ItemCode;

  @Max(2)
  @NotBlank
  private String AffairType;

  @Max(50)
  private String OrderCode;

  @NotNull
  private Integer FlagConfig;

  @NotNull
  private Integer Vehicle;

  @Max(6)
  private String POPickupCode;

  @Max(10)
  private String PickupDateTime;

  @Max(5000)
  private String Contents;

  private Integer CODAmount;

  private Receiver Receiver;

  private List<Pack> Package;

  private List<AddOn> Addons;

  @Getter
  @Setter
  public static class Receiver {

    @Max(100)
    private String OrgCode;

    @Max(150)
    private String CustomerName;

    @Max(100)
    private String ContractNumber;

    @Max(150)
    private String Fullname;

    @Max(10)
    private String Phone;

    @Max(150)
    private String Address;

    @Max(5)
    private String Postcode;

    @Max(2)
    private String Province;

    @Max(50)
    private String ProvinceName;

    @Max(4)
    private String District;

    @Max(50)
    private String DistrictName;

    @Max(5)
    private String Commune;

    @Max(50)
    private String CommuneName;

    @Max(100)
    private String Email;

    @Max(100)
    private String Lon;

    @Max(100)
    private String Lat;

    @Max(100)
    private String Vpostcode;

  }

  @Getter
  @Setter
  public static class Pack {

    private Integer Weight;

    private Integer Length;

    private Integer Height;

    private Integer isVolumn;

    @Max(10)
    private String Volume;

    private Integer PriceWeight;

    private Integer DimWeight;

  }

  @Getter
  @Setter
  public static class AddOn {

    @Max(6)
    private String ServiceCode;

    private List<Prop> Props;

    @Getter
    @Setter
    public static class Prop {

      @Max(20)
      private String PropCode;

      @Max(100)
      private String PropValue;
    }
  }
}
