package com.datn.watch.salespromotion.promotion.repository;

import com.pet.market.api.common.constant.TableConstant;
import com.pet.market.api.salespromotion.promotion.model.entity.PromotionCust;
import org.apache.ibatis.jdbc.SQL;

public class PromotionCustSqlBuilder {

    public String save(PromotionCust object) {
        return new SQL() {{
            FROM(TableConstant.TB_PROMOTION_CUSTOMER);
            VALUES("created_by, created_date", "#{createdBy}, now()");
            VALUES("updated_by, updated_date", "#{lastModifiedBy}, now()");
            VALUES("ID_PROMOTION", "#{idPromotion}");
            VALUES("ID_CUSTOMER", "#{idCustomer}");
            VALUES("ID_ORDER", "#{orderId}");
            VALUES("IS_ADD_DISCOUNT", "#{isAddDiscount}");
        }}.toString();
    }

    public String update(PromotionCust object) {
        return new SQL() {{
            FROM(TableConstant.TB_PROMOTION_CUSTOMER);
            SET("updated_by = #{lastModifiedBy}");
            SET("updated_date = now()");

            if (null != object.getIdPromotion()) {
                SET("ID_PROMOTION", "#{idPromotion}");
            }
            if (null != object.getIdCustomer()) {
                SET("ID_CUSTOMER", "#{idCustomer}");
            }
            if (null != object.getIsAddDiscount()) {
                SET("IS_ADD_DISCOUNT", "#{isAddDiscount}");
            }
            WHERE("ID = #{id}");
        }}.toString();
    }
}
