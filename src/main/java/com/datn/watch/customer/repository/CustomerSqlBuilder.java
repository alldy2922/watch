package com.datn.watch.customer.repository;

import com.pet.market.api.customer.model.CustomerLisReq;
import com.pet.market.api.customer.model.UpdateStatusDto;
import com.pet.market.api.customer.model.entity.Customer;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

import static com.pet.market.api.common.constant.TableConstant.*;

public class CustomerSqlBuilder {
    public String findAll(CustomerLisReq req){
        return new SQL() {{
            SELECT("id, full_name, phone_number phoneNumber, address, status, PROVINCE, DISTRICT, COMMUNE");
            FROM(TB_CUSTOMER).WHERE("1 = 1");
            if(StringUtils.isNotBlank(req.getPhoneNumber())){
                AND().WHERE("phone_number like concat('%', #{phoneNumber}, '%')");
            }
            if (StringUtils.isNotBlank(req.getSearch())) {
                AND().WHERE("( phone_number like concat('%', #{search}, '%') OR full_name like concat('%', #{search}, '%') )");
            }
            if (ObjectUtils.isNotEmpty(req.getActive())) {
                AND().WHERE("status = #{active}");
            }
            ORDER_BY("created_date");
            LIMIT(req.getPageSize());
            OFFSET(req.getOffset());
        }}.toString();
    }

    public String listAll(CustomerLisReq req){
        return new SQL() {{
            SELECT("A.id, A.full_name, A.phone_number, A.address, A.status, A.PROVINCE, A.DISTRICT, A.COMMUNE, B.NAME AS provinceName, C.NAME AS districtName, D.NAME AS communeName ");
            FROM(TB_CUSTOMER + " A").INNER_JOIN(TB_AREA_PROVINCE + " B on A.PROVINCE = B.ID ")
                    .INNER_JOIN(TB_AREA_DISTRICT + " C on A.DISTRICT = C.ID ").
                    INNER_JOIN(TB_AREA_COMMUNE + " D on A.COMMUNE = D.ID ")
                    .WHERE("1 = 1");
            if(StringUtils.isNotBlank(req.getPhoneNumber())){
                AND().WHERE("A.phone_number like concat('%', #{phoneNumber}, '%')");
            }
            if (StringUtils.isNotBlank(req.getSearch())) {
                AND().WHERE("( A.phone_number like concat('%', #{search}, '%') OR A.full_name like concat('%', #{search}, '%') )");
            }
            if (ObjectUtils.isNotEmpty(req.getActive())) {
                AND().WHERE("A.status = #{active}");
            }
            ORDER_BY("A.created_date");
            LIMIT(req.getPageSize());
            OFFSET(req.getOffset());
        }}.toString();
    }

    public String count(CustomerLisReq req){
        return new SQL() {{
            SELECT("count(id)");
            FROM(TB_CUSTOMER).WHERE(" 1 = 1 ");
            if(StringUtils.isNotBlank(req.getPhoneNumber())){
                AND().WHERE("phone_number like concat('%', #{phoneNumber}, '%')");
            }
            if (StringUtils.isNotBlank(req.getSearch())) {
                AND().WHERE("( phone_number like concat('%', #{search}, '%') OR full_name like concat('%', #{search}, '%') )");
            }
            if (ObjectUtils.isNotEmpty(req.getActive())) {
                AND().WHERE("status = #{active}");
            }
        }}.toString();
    }

    public String delete(List<String> ids){
        return new SQL() {{
            String param = StringUtils.join(ids, ",");
            DELETE_FROM(TB_CUSTOMER);
            WHERE("id in ( "+param+" )");
        }}.toString();
    }

    public String updateStatus(UpdateStatusDto rq){
        return new SQL() {{
            UPDATE(TB_CUSTOMER);
            SET("status = #{status}");
            WHERE("id = #{id}");
        }}.toString();
    }

    public String save(Customer customer){
        return new SQL() {{
            INSERT_INTO(TB_CUSTOMER);
            VALUES("created_by, created_date", "#{createdBy},#{createdDate}");
            VALUES("id, full_name, phone_number, province","#{phoneNumber},#{fullName},#{phoneNumber},#{province}");
            VALUES("district, commune, address, status","#{district},#{commune},#{address},#{status}");
            VALUES("gender, birth_day, type, tax_code","#{gender},#{birthDay},#{type},#{taxCode}");
            VALUES("email, facebook, customer_group, note","#{email},#{facebook},#{customerGroup},#{note}");
        }}.toString();
    }

    public String findById(String id){
        return new SQL() {{
            SELECT("id, full_name, phone_number phoneNumber, province, district, commune, address, status");
            SELECT("gender, birth_day birthDay, type, tax_code taxCode");
            SELECT("email, facebook, customer_group customerGroup, note");
            FROM(TB_CUSTOMER);
            WHERE("id = #{id}");
        }}.toString();
    }

    public String update(Customer customer){
        return new SQL() {{
            UPDATE(TB_CUSTOMER);
            SET("full_name = #{fullName}");
            //SET("phone_number = #{phoneNumber}");
            SET("province = #{province}");
            SET("district = #{district}");
            SET("commune = #{commune}");
            SET("address = #{address}");
            SET("gender = #{gender}");
            SET("birth_day = #{birthDay}");
            SET("type = #{type}");
            SET("tax_code = #{taxCode}");
            SET("email = #{email}");
            SET("facebook = #{facebook}");
            SET("customer_group = #{customerGroup}");
            SET("note = #{note}");
            SET("status = #{status}");
            SET("updated_by = #{lastModifiedBy}");
            SET("updated_date = #{lastModifiedDate}");
            WHERE("id = #{id}");
        }}.toString();
    }

    public String checkPhoneNumber(String id, String phoneNumber, Integer type){
        return new SQL() {{
            SELECT("count(id)");
            FROM(TB_CUSTOMER);
            if(type == 0){
                WHERE("phone_number = #{phoneNumber}");
            }else{
                WHERE("id <> #{id}");
                WHERE("phone_number = #{phoneNumber}");
            }
        }}.toString();
    }
}
