package com.datn.watch.api.merchandise.review.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.datn.watch.api.merchandise.category.model.dto.CategoryDto;
import com.datn.watch.api.merchandise.review.model.dto.ReviewDto;
import com.datn.watch.api.merchandise.review.model.entity.Review;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ReviewRepository {

    @SelectProvider(type = ReviewSqlBuilder.class, method = "getAll")
    List<Review> getAll(ReviewDto.ListReq req);

    @SelectProvider(type = ReviewSqlBuilder.class, method = "findAll")
    List<Review> findAll(ReviewDto.ListReq req);

    @SelectProvider(type = ReviewSqlBuilder.class, method = "getById")
    Optional<Review> getCategoryById(Long id);

    @SelectProvider(type = ReviewSqlBuilder.class, method = "save")
    void save(Review review);

    @SelectProvider(type = ReviewSqlBuilder.class, method = "delete")
    void delete(Long id);
    @SelectProvider(type = ReviewSqlBuilder.class, method = "deletes")
    void deletes(ReviewDto.DeleteReq req);

    @SelectProvider(type = ReviewSqlBuilder.class, method = "update")
    void update(Review brand);

    @SelectProvider(type = ReviewSqlBuilder.class, method = "count")
    int count();

    @SelectProvider(type = ReviewSqlBuilder.class, method = "updateStatus")
    void updateStatus(ReviewDto.StatusReq req);


}
