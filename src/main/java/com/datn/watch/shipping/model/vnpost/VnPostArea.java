package com.datn.watch.shipping.model.vnpost;

import lombok.Getter;
import lombok.Setter;

public class VnPostArea {

  @Getter
  @Setter
  public static class Province {

    private String provinceCode;

    private String provinceName;

  }

  @Getter
  @Setter
  public static class District {

    private String districtCode;

    private String districtName;

    private String provinceCode;

  }

  @Getter
  @Setter
  public static class Commune {

    private String communeCode;

    private String communeName;

    private String districtCode;

  }

}
