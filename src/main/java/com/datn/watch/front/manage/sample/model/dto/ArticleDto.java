package com.datn.watch.front.manage.sample.model.dto;

import com.datn.watch.common.utils.ModelMapperUtils;
import com.datn.watch.common.utils.paging.Search;
import com.datn.watch.front.manage.sample.model.entity.Article;
import lombok.Data;
import lombok.EqualsAndHashCode;

public class ArticleDto {
    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class ArticleListReq extends Search {
        Boolean enabledPage  = true;

    }

    @Data
    public static class Request {
        private long id;
        private String title;
        private String author;
        public Article toEntity() {
            return ModelMapperUtils.map(this, Article.class);
        }
    }
    @Data
    public static class Response {
        private long id;
        private String title;
        private String author;
    }
}
