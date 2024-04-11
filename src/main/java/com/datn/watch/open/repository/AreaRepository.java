package com.datn.watch.open.repository;

import com.pet.market.api.common.model.criteria.SearchCriteria;
import com.pet.market.api.interlock.shipping.model.vnpost.VnPostArea.Commune;
import com.pet.market.api.interlock.shipping.model.vnpost.VnPostArea.District;
import com.pet.market.api.interlock.shipping.model.vnpost.VnPostArea.Province;
import com.pet.market.api.open.model.Area;
import com.pet.market.api.open.model.criteria.AreaCriteria;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

import static com.pet.market.api.common.constant.TableConstant.*;

@Mapper
public interface AreaRepository {

    @SelectProvider(type= AreaSqlBuilder.class, method="getAllProvince")
    List<Area> getAllProvince(SearchCriteria criteria);

    @SelectProvider(type= AreaSqlBuilder.class, method="getAllDistrict")
    List<Area> getAllDistrict(AreaCriteria criteria);

    @SelectProvider(type= AreaSqlBuilder.class, method="getAllCommune")
    List<Area> getAllCommune(AreaCriteria criteria);

    @Insert("""
            INSERT INTO TB_AREA_PROVINCE
            (ID, NAME)
            VALUES(#{provinceCode}, #{provinceName})
            """)
    void saveProvince(Province params);

    @Insert("""
            INSERT INTO TB_AREA_DISTRICT
            (ID, NAME, PROVINCE_ID)
            VALUES(#{districtCode}, #{districtName}, #{provinceCode})
            """)
    void saveDistrict(District params);

    @Insert("""
            INSERT INTO TB_AREA_COMMUNE
            (ID, NAME, DISTRICT_CODE)
            VALUES(#{communeCode}, #{communeName}, #{districtCode})
            """)
    void saveCommune(Commune params);

    @Delete("DELETE FROM TB_AREA_PROVINCE")
    void deleteProvince();

    @Delete("DELETE FROM TB_AREA_DISTRICT")
    void deleteDistrict();

    @Delete("DELETE FROM TB_AREA_COMMUNE")
    void deleteCommune();
}
