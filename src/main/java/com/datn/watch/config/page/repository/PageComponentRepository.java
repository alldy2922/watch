package com.datn.watch.config.page.repository;

import com.pet.market.api.config.page.modal.entity.PageComponent;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

@Mapper
public interface PageComponentRepository {
    @InsertProvider(type= PageComponentSqlBuilder.class, method="save")
    void save(PageComponent pageComponent);

    @SelectProvider(type= PageComponentSqlBuilder.class, method="findByIdPage")
    List<PageComponent> findByIdPage(long idPage);

    @SelectProvider(type= PageComponentSqlBuilder.class, method="countByIdComponent")
    int countByIdComponent(long idComponent);

    @DeleteProvider(type= PageComponentSqlBuilder.class, method="delete")
    void delete(long idPage);
}
