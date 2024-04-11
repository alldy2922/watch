package com.datn.watch.shipping.repository;

import com.pet.market.api.shipping.model.Gtgt;
import com.pet.market.api.shipping.model.Prop;
import com.pet.market.api.shipping.model.Spdv;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

import static com.pet.market.api.common.constant.TableConstant.*;

@Mapper
public interface VnPostRepository {

    @Select("SELECT ID as code, NAME FROM " + TB_VN_POST_SPDV_CATE)
    List<Spdv> getAllSpdv();

    @Select("SELECT GTGT_ID as code, NAME, SPDV_ID as 'spdv', DV_TYPE as type FROM " + TB_VN_POST_GTGT_CATE + " WHERE SPDV_ID = #{spdv} ")
    List<Gtgt> getAllGtgt(String spdv);

    @Select("SELECT ID as code, NAME, GTGT_ID as 'gtgt', TYPE FROM " + TB_VN_POST_GTGT_PROPS + " WHERE GTGT_ID = #{gtgt} ")
    List<Prop> getAllProp(String gtgt);

}
