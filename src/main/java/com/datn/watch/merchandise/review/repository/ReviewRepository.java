package com.datn.watch.merchandise.review.repository;

import com.pet.market.api.merchandise.review.model.dto.ReviewDto;
import com.pet.market.api.merchandise.review.model.entity.Review;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

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
