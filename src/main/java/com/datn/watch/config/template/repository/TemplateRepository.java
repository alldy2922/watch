package com.datn.watch.config.template.repository;

import com.pet.market.api.common.model.entity.UpdateStatusRq;
import com.pet.market.api.config.template.modal.TemplateListReq;
import com.pet.market.api.config.template.modal.TemplateOptionDto;
import com.pet.market.api.config.template.modal.entity.Template;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TemplateRepository {
    @SelectProvider(type=TemplateSqlBuilder.class, method="findAll")
    List<Template> findAll(TemplateListReq req);

    @SelectProvider(type=TemplateSqlBuilder.class, method="count")
    int count(TemplateListReq req);

    @UpdateProvider(type=TemplateSqlBuilder.class, method="updateStatus")
    void updateStatus(UpdateStatusRq rq);

    @InsertProvider(type=TemplateSqlBuilder.class, method="save")
    void save(Template template);

    @SelectProvider(type=TemplateSqlBuilder.class, method="findById")
    Template findById(long id);

    @UpdateProvider(type=TemplateSqlBuilder.class, method="update")
    void update(Template template);

    @DeleteProvider(type=TemplateSqlBuilder.class, method="delete")
    void delete(List<Long> ids);

    @SelectProvider(type=TemplateSqlBuilder.class, method="selectAll")
    List<TemplateOptionDto> selectAll();
}
