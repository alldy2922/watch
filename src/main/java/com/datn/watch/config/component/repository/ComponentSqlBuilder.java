package com.datn.watch.config.component.repository;

import com.pet.market.api.common.model.entity.UpdateStatusRq;
import com.pet.market.api.config.component.modal.ComponentListReq;
import com.pet.market.api.config.component.modal.entity.Component;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

import static com.pet.market.api.common.constant.TableConstant.TB_COMPONENT;

public class ComponentSqlBuilder {

    public String findAll(ComponentListReq req){
        return new SQL() {{
            SELECT("id, name, template_name templateName, status");
            FROM(TB_COMPONENT);
            if(StringUtils.isNotBlank(req.getName())){
                WHERE("name like concat('%', #{name}, '%')");
            }
            ORDER_BY("created_date");
            LIMIT(req.getPageSize());
            OFFSET(req.getOffset());
        }}.toString();
    }

    public String count(ComponentListReq req){
        return new SQL() {{
            SELECT("count(id)");
            FROM(TB_COMPONENT);
            if(StringUtils.isNotBlank(req.getName())){
                WHERE("name like concat('%', #{name}, '%')");
            }
        }}.toString();
    }

    public String delete(List<Long> ids){
        return new SQL() {{
            String param = StringUtils.join(ids, ",");
            DELETE_FROM(TB_COMPONENT);
            WHERE("id in ( "+param+" )");
        }}.toString();
    }

    public String updateStatus(UpdateStatusRq rq){
        return new SQL() {{
            UPDATE(TB_COMPONENT);
            SET("status = #{status}");
            WHERE("id = #{id}");
        }}.toString();
    }

    public String save(Component component){
        return new SQL() {{
            INSERT_INTO(TB_COMPONENT);
            VALUES("created_by, created_date", "#{createdBy},#{createdDate}");
            VALUES("name, data, template_data","#{name},#{data},#{templateData}");
            VALUES("template_name, id_template, html_admin","#{templateName},#{idTemplate},#{htmlAdmin}");
            VALUES("html_user, img_preview, status","#{htmlUser},#{imgPreview},#{status}");
        }}.toString();
    }

    public String findById(long id){
        return new SQL() {{
            SELECT("id, name, template_data templateData, template_name templateName, img_preview imgPreview, status");
            FROM(TB_COMPONENT);
            WHERE("id = #{id}");
        }}.toString();
    }

    public String update(Component component){
        return new SQL() {{
            UPDATE(TB_COMPONENT);
            SET("name = #{name}");
            SET("data = #{data}");
            SET("template_data = #{templateData}");
            if(StringUtils.isNotBlank(component.getTemplateName())){
                SET("template_name = #{templateName}");
            }
            if(StringUtils.isNotBlank(component.getImgPreview())) {
                SET("img_preview = #{imgPreview}");
            }
            if(StringUtils.isNotBlank(component.getHtmlAdmin())) {
                SET("html_admin = #{htmlAdmin}");
            }
            if(StringUtils.isNotBlank(component.getHtmlUser())) {
                SET("html_user = #{htmlUser}");
            }
            if(StringUtils.isNotBlank(component.getHtmlUser())) {
                SET("id_template = #{idTemplate}");
            }
            SET("status = #{status}");
            SET("updated_by = #{lastModifiedBy}");
            SET("updated_date = #{lastModifiedDate}");
            WHERE("id = #{id}");
        }}.toString();
    }

    public String findByStatus(){
        return new SQL() {{
            SELECT("id, name, status");
            FROM(TB_COMPONENT);
            WHERE("status = 1");
        }}.toString();
    }
}
