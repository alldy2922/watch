package com.datn.watch.order.service;

import com.pet.market.api.order.model.OrderItem;
import com.pet.market.api.order.repository.OrderDetailRepository;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderDetailsService {

  private final SqlSessionFactory sqlSessionFactory;

  public OrderDetailsService(SqlSessionFactory sessionFactory) {
    this.sqlSessionFactory = sessionFactory;
  }

  @Transactional
  public void create(List<OrderItem> items) {

    try (SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH)) {
      OrderDetailRepository mapper = sqlSession.getMapper(OrderDetailRepository.class);
      for (OrderItem item: items) {
        mapper.save(item);
      }
      sqlSession.commit();
    }
  }



}
