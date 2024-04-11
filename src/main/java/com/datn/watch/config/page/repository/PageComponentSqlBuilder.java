package com.datn.watch.config.page.repository;

import com.pet.market.api.config.page.modal.entity.PageComponent;
import org.apache.ibatis.jdbc.SQL;

public class PageComponentSqlBuilder {
    private static final String TB_PAGE_COMPONENT = "TB_PAGE_COMPONENT";

    public String save(PageComponent pageComponent){
        return new SQL() {{
            INSERT_INTO(TB_PAGE_COMPONENT);
            VALUES("created_by, created_date", "#{createdBy},#{createdDate}");
            VALUES("id_component, id_page","#{idComponent},#{idPage}");
        }}.toString();
    }

    public String findByIdPage(long idPage){
        return new SQL() {{
            SELECT("id, id_component idComponent, id_page idPage");
            FROM(TB_PAGE_COMPONENT);
            WHERE("id_page = #{idPage}");
        }}.toString();
    }

    public String countByIdComponent(long idComponent){
        return new SQL() {{
            SELECT("count(id)");
            FROM(TB_PAGE_COMPONENT);
            WHERE("id_component = #{idComponent}");
        }}.toString();
    }

    public String delete(long idPage){
        return new SQL() {{
            DELETE_FROM(TB_PAGE_COMPONENT);
            WHERE("id_page = #{idPage}");
        }}.toString();
    }
}
