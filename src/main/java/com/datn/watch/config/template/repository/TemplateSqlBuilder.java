package com.datn.watch.config.template.repository;

import com.pet.market.api.common.model.entity.UpdateStatusRq;
import com.pet.market.api.config.template.modal.TemplateListReq;
import com.pet.market.api.config.template.modal.entity.Template;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

import static com.pet.market.api.common.constant.TableConstant.TB_TEMPLATE;

public class TemplateSqlBuilder {

    public String findAll(TemplateListReq req){
        return new SQL() {{
            SELECT("id, template_name templateName,type, html_admin htmlAdmin, html_user htmlUser, img_preview imgPreview,status");
            FROM(TB_TEMPLATE);
            if(StringUtils.isNotBlank(req.getTemplateName())){
                WHERE("template_name like concat('%', #{templateName}, '%')");
            }
            ORDER_BY("created_date");
            LIMIT(req.getPageSize());
            OFFSET(req.getOffset());
        }}.toString();
    }

    public String count(TemplateListReq req){
        return new SQL() {{
            SELECT("count(id)");
            FROM(TB_TEMPLATE);
            if(StringUtils.isNotBlank(req.getTemplateName())){
                WHERE("template_name like concat('%', #{templateName}, '%')");
            }
        }}.toString();
    }

    public String updateStatus(UpdateStatusRq rq){
        return new SQL() {{
            UPDATE(TB_TEMPLATE);
            SET("status = #{status}");
            WHERE("id = #{id}");
        }}.toString();
    }

    public String save(Template template){
        return new SQL() {{
            INSERT_INTO(TB_TEMPLATE);
            VALUES("created_by, created_date", "#{createdBy},#{createdDate}");
            VALUES("template_name, type, html_admin","#{templateName},#{type},#{htmlAdmin}");
            VALUES("html_user, img_preview, status","#{htmlUser},#{imgPreview},#{status}");
        }}.toString();
    }

    public String findById(long id){
        return new SQL() {{
            SELECT("id, template_name templateName,type, html_admin htmlAdmin, html_user htmlUser, img_preview imgPreview,status");
            FROM(TB_TEMPLATE);
            WHERE("id = #{id}");
        }}.toString();
    }

    public String update(Template template){
        return new SQL() {{
           UPDATE(TB_TEMPLATE);
           if(StringUtils.isNotBlank(template.getTemplateName())){
               SET("template_name = #{templateName}");
           }
           if(StringUtils.isNotBlank(template.getHtmlAdmin())){
               SET("html_admin = #{htmlAdmin}");
           }
           if(StringUtils.isNotBlank(template.getHtmlUser())){
               SET("html_user = #{htmlUser}");
           }
           if(StringUtils.isNotBlank(template.getImgPreview())){
               SET("img_preview = #{imgPreview}");
           }
           SET("status = #{status}");
           SET("updated_by = #{lastModifiedBy}");
           SET("updated_date = #{lastModifiedDate}");
           WHERE("id = #{id}");
        }}.toString();
    }

    public String delete(List<Long> ids){
        return new SQL() {{
            String param = StringUtils.join(ids, ",");
            DELETE_FROM(TB_TEMPLATE);
            WHERE("id in ( "+param+" )");
        }}.toString();
    }

    public String selectAll(){
        return new SQL() {{
            SELECT("id, template_name name");
            FROM(TB_TEMPLATE);
            WHERE("status = 1");
        }}.toString();
    }

}
