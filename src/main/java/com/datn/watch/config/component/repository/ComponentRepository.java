package com.datn.watch.config.component.repository;

import com.pet.market.api.config.component.modal.ComponentListReq;
import com.pet.market.api.config.component.modal.entity.Component;
import com.pet.market.api.common.model.entity.UpdateStatusRq;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ComponentRepository {
    @SelectProvider(type=ComponentSqlBuilder.class, method="findAll")
    List<Component> findAll(ComponentListReq req);
    @SelectProvider(type=ComponentSqlBuilder.class, method="count")
    int count(ComponentListReq req);
    @DeleteProvider(type=ComponentSqlBuilder.class, method="delete")
    void delete(List<Long> ids);
    @UpdateProvider(type=ComponentSqlBuilder.class, method="updateStatus")
    void updateStatus(UpdateStatusRq rq);
    @InsertProvider(type=ComponentSqlBuilder.class, method="save")
    void save(Component component);
    @SelectProvider(type=ComponentSqlBuilder.class, method="findById")
    Component findById(long id);
    @UpdateProvider(type=ComponentSqlBuilder.class, method="update")
    void update(Component component);
    @SelectProvider(type=ComponentSqlBuilder.class, method="findByStatus")
    List<Component> findByStatus();
}
