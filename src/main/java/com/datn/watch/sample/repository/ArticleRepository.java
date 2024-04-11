package com.datn.watch.sample.repository;

import com.datn.watch.sample.model.dto.ArticleDto;
import com.datn.watch.sample.model.entity.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ArticleRepository {

  @Select("select id, title, author from articles order by id LIMIT #{pageSize} OFFSET #{offset}")
  List<Article> getAll(ArticleDto.ArticleListReq req);

  @Select("select count(id) from articles")
  int count();
  @Select("select * from articles")
  List<Article> findAll(ArticleDto.ArticleListReq req);
  @Select("select * from articles where id = #{id}")
  Optional<Article> getArticleById(Long id);

  @Insert("INSERT INTO ARTICLES (id, title, author) VALUES (#{id}, #{title}, #{author})")
  void save(Article article);

  @Delete("DELETE FROM market.articles WHERE id=#{id}")
  void delete(Long id);

  @Update("UPDATE market.articles SET title=#{title} ,author=#{author} WHERE id=#{id}")
  void update(Article entity);
}
