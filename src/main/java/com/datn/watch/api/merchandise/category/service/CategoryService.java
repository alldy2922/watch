package com.datn.watch.api.merchandise.category.service;

import com.datn.watch.api.merchandise.category.model.dto.CategoryDto;
import com.datn.watch.api.merchandise.category.model.dto.CategoryDto.CategoryReq;
import com.datn.watch.api.merchandise.category.model.dto.CategoryDto.CategoryResp;
import com.datn.watch.api.merchandise.category.model.entity.Category;
import com.datn.watch.api.merchandise.category.repository.CategoryRepository;
import com.datn.watch.common.model.json.ResultData;
import com.datn.watch.common.utils.ModelMapperUtils;
import com.datn.watch.common.utils.paging.ResultPageData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author tobi
 */
@Service
@Slf4j
public class CategoryService {

    private final CategoryRepository mapper;

    public CategoryService(CategoryRepository mapper) {
        this.mapper = mapper;
    }

    public Object gets(CategoryDto.ListReq req) {
        if (req.getEnabledPage()) {
            int totalRecords = mapper.count(req);
            List<Category> listArticle = mapper.getAll(req);
            return new ResultPageData<>(req.getPageNo(), req.getPageSize(), totalRecords, ModelMapperUtils.mapAll(listArticle, CategoryResp.class));
        } else {
            return new ResultData<>(ModelMapperUtils.mapAll(mapper.findAll(req), CategoryResp.class));
        }
    }

    public Object get(Long id) {
        Optional<Category> Category = mapper.getById(id);
        return Category.map(value -> ModelMapperUtils.map(value, CategoryResp.class)).orElse(null);
    }

    public void save(CategoryReq req) {
        mapper.save(req.toEntity());
    }

    public void update(CategoryReq req) {
        mapper.update(req.toEntity());
    }

    public void delete(Long id) {
        mapper.delete(id);
    }

    public void deletes(CategoryDto.DeleteReq req) {
        mapper.deletes(req);
    }

    public void updateStatus(CategoryDto.StatusReq req) {
        mapper.updateStatus(req);
    }

    public Object getAttributes(CategoryDto.IdsReq req) {
        Set<Category> categories = getAllAttributes(new HashSet<>(), new HashSet<>(List.of(req.getIds())));
        return new ResultData<>(ModelMapperUtils.mapAll(categories, CategoryDto.CategoryAttrResp.class));
    }

    public Set<Category> getAllAttributes(Set<Category> categories, Set<Long> parentIds) {
        if (!parentIds.isEmpty() && !(parentIds.size() == 1 && parentIds.contains(0L))) {
            categories = mapper.getAttributes(new CategoryDto.IdsReq(parentIds.toArray(Long[]::new)));
            var newParentIds = categories.stream()
                    .map(Category::getParent)
                    .filter(parent -> parent != null && parent != 0L)
                    .collect(Collectors.toSet());
            categories = Stream.concat(categories.stream(), getAllAttributes(categories, newParentIds).stream())
                    .collect(Collectors.toUnmodifiableSet());
        }
        return categories;
    }
}
