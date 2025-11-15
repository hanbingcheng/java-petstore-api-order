package com.example.petstore.api.order.domain.repository;

import com.example.petstore.api.order.domain.model.OrderEntity;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderRepository {
  List<OrderEntity> findByStatus(String status, Long userId);

  OrderEntity getOrderById(long orderId);

  void insert(OrderEntity order);

  void update(OrderEntity order);
}
