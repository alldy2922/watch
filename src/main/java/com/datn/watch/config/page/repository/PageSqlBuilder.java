package com.datn.watch.config.page.repository;

import com.pet.market.api.common.model.entity.UpdateStatusRq;
import com.pet.market.api.config.page.modal.PageListReq;
import com.pet.market.api.config.page.modal.entity.Page;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

import static com.pet.market.api.common.constant.TableConstant.TB_PAGE;

public class PageSqlBuilder {

    public String findAll(PageListReq req){
        return new SQL() {{
            SELECT("id, name, status");
            FROM(TB_PAGE);
            if(StringUtils.isNotBlank(req.getName())){
                WHERE("name like concat('%', #{name}, '%')");
            }
            ORDER_BY("created_date");
            LIMIT(req.getPageSize());
            OFFSET(req.getOffset());
        }}.toString();
    }

    public String count(PageListReq req){
        return new SQL() {{
            SELECT("count(id)");
            FROM(TB_PAGE);
            if(StringUtils.isNotBlank(req.getName())){
                WHERE("name like concat('%', #{name}, '%')");
            }
        }}.toString();
    }

    public String delete(List<Long> ids){
        return new SQL() {{
            String param = StringUtils.join(ids, ",");
            DELETE_FROM(TB_PAGE);
            WHERE("id in ( "+param+" )");
        }}.toString();
    }

    public String updateStatus(UpdateStatusRq rq){
        return new SQL() {{
            UPDATE(TB_PAGE);
            SET("status = #{status}");
            WHERE("id = #{id}");
        }}.toString();
    }

    public String save(Page page){
        return new SQL() {{
            INSERT_INTO(TB_PAGE);
            VALUES("created_by, created_date", "#{createdBy},#{createdDate}");
            VALUES("name, data, type, html_data, status","#{name},#{data},#{type},#{htmlData},#{status}");
        }}.toString();
    }

    public String findById(long id){
        return new SQL() {{
            SELECT("id, name, type, data, html_data htmlData, status");
            FROM(TB_PAGE);
            WHERE("id = #{id}");
        }}.toString();
    }

    public String update(Page page){
        return new SQL() {{
            UPDATE(TB_PAGE);
            SET("name = #{name}");
            SET("data = #{data}");
            SET("html_data = #{htmlData}");
            SET("status = #{status}");
            SET("updated_by = #{lastModifiedBy}");
            SET("updated_date = #{lastModifiedDate}");
            WHERE("id = #{id}");
        }}.toString();
    }

}
