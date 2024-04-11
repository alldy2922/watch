package com.datn.watch.merchandise.category.repository;

import com.pet.market.api.merchandise.category.model.dto.CategoryDto;
import com.pet.market.api.merchandise.category.model.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Mapper
public interface CategoryRepository {


    @SelectProvider(type = CategorySqlBuilder.class, method = "getAll")
    List<Category> getAll(CategoryDto.ListReq req);

    @SelectProvider(type = CategorySqlBuilder.class, method = "findAll")
    List<Category> findAll(CategoryDto.ListReq req);

    @SelectProvider(type = CategorySqlBuilder.class, method = "getById")
    Optional<Category> getById(Long id);

    @SelectProvider(type = CategorySqlBuilder.class, method = "save")
    void save(Category Category);

    @SelectProvider(type = CategorySqlBuilder.class, method = "delete")
    void delete(Long id);
    @SelectProvider(type= CategorySqlBuilder.class, method="deletes")
    void deletes(CategoryDto.DeleteReq ids);
    @SelectProvider(type = CategorySqlBuilder.class, method = "update")
    void update(Category entity);
    @SelectProvider(type = CategorySqlBuilder.class, method = "count")
    int count(CategoryDto.ListReq req);

    @SelectProvider(type = CategorySqlBuilder.class, method = "updateStatus")
    void updateStatus(CategoryDto.StatusReq req);
    @SelectProvider(type = CategorySqlBuilder.class, method = "getAttributes")
    Set<Category> getAttributes(CategoryDto.IdsReq req);
}
