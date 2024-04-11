package com.datn.watch.sample.service;

import com.datn.watch.common.utils.ModelMapperUtils;
import com.datn.watch.common.utils.paging.ResultPageData;
import com.datn.watch.sample.model.dto.ArticleDto;
import com.datn.watch.sample.model.entity.Article;
import com.datn.watch.sample.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author tobi
 */
@Service
@Slf4j
public class ArticleService {

    @Autowired
    ArticleRepository mapper;


    //  public ResponseEntity<APIResponse> gets() {
//    return ResponseEntity.ok(
//        APIResponseBuilder.buildResponse(HttpStatus.OK,
//            ModelMapperUtils.mapAll(repository.findAll(), ArticleResp.class)));
//  }
    public ResultPageData<List<Article>> gets(ArticleDto.ArticleListReq req) {

        log.info("demo");

        //demo with param (currentPage, pageSize)
        int totalRecords = mapper.count();
        List<Article> listArticle = mapper.getAll(req);
        ResultPageData<List<Article>> pagingData = new ResultPageData(req.getPageNo(),req.getPageSize(),totalRecords,listArticle);
        return pagingData;
    }

    public Object get(Long id) {
        Optional<Article> article = mapper.getArticleById(id);
        return article.map(value -> ModelMapperUtils.map(value, ArticleDto.Response.class)).orElse(null);
    }

    public void save(ArticleDto.Request req) {
        mapper.save(req.toEntity());
    }

    public void update(ArticleDto.Request req) {
        mapper.update(req.toEntity());
    }

    public void delete(Long id) {
        mapper.delete(id);
    }
}
