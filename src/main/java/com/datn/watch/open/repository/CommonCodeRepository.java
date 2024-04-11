package com.datn.watch.open.repository;

import com.pet.market.api.open.model.dto.CommonDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommonCodeRepository {

    @Select("SELECT CODE AS 'value', NAME AS 'name' FROM TB_COMMON_CODE WHERE GROUP_CODE = #{group}")
    List<CommonDTO.CodeRes> listByGroup(String group);

}
