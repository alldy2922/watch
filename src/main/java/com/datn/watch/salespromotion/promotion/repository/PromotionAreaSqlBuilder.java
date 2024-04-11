package com.datn.watch.salespromotion.promotion.repository;

import com.pet.market.api.common.constant.TableConstant;
import com.pet.market.api.salespromotion.promotion.model.entity.PromotionArea;
import org.apache.ibatis.jdbc.SQL;

public class PromotionAreaSqlBuilder {

    public String save(PromotionArea object) {
        return new SQL() {{
            INSERT_INTO(TableConstant.TB_PROMOTION_AREA);
            VALUES("created_by, created_date", "#{createdBy},now()");
            VALUES("updated_by, updated_date", "#{lastModifiedBy},now()");
            VALUES("ID_PROMOTION", "#{idPromotion}");
            VALUES("ID_AREA", "#{idArea}");
            VALUES("ID_ORDER", "#{idOrder}");
            VALUES("IS_ADD_DISCOUNT", "#{isAddDiscount}");
        }}.toString();
    }
    public String update(PromotionArea object) {
        return new SQL() {{
            FROM(TableConstant.TB_PROMOTION_AREA);
            SET("updated_by = #{lastModifiedBy}");
            SET("updated_date = now()");

            if (null != object.getIdPromotion()) {
                SET("ID_PROMOTION", "#{idPromotion}");
            }
            if (null != object.getIdArea()) {
                SET("ID_AREA", "#{idArea}");
            }
            if (null != object.getIsAddDiscount()) {
                SET("IS_ADD_DISCOUNT", "#{isAddDiscount}");
            }
            WHERE("ID = #{id}");
        }}.toString();
    }
}
