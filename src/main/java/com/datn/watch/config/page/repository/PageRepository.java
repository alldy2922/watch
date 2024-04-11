package com.datn.watch.config.page.repository;

import com.pet.market.api.common.model.entity.UpdateStatusRq;
import com.pet.market.api.config.page.modal.PageListReq;
import com.pet.market.api.config.page.modal.entity.Page;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PageRepository {
    @SelectProvider(type= PageSqlBuilder.class, method="findAll")
    List<Page> findAll(PageListReq req);

    @SelectProvider(type= PageSqlBuilder.class, method="count")
    int count(PageListReq req);

    @DeleteProvider(type= PageSqlBuilder.class, method="delete")
    void delete(List<Long> ids);

    @UpdateProvider(type= PageSqlBuilder.class, method="updateStatus")
    void updateStatus(UpdateStatusRq rq);

    @InsertProvider(type= PageSqlBuilder.class, method="save")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void save(Page page);

    @SelectProvider(type= PageSqlBuilder.class, method="findById")
    Page findById(long id);

    @UpdateProvider(type= PageSqlBuilder.class, method="update")
    void update(Page page);
}
