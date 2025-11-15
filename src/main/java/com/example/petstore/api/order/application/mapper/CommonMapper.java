package com.example.petstore.api.order.application.mapper;

import com.example.petstore.api.order.domain.model.OrderEntity;
import com.example.petstore.api.order.oas.model.Order;
import com.example.petstore.api.order.oas.model.OrderStatus;
import com.example.petstore.api.order.oas.model.Pager;
import com.github.pagehelper.PageInfo;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CommonMapper {

  public Pager mapPager(PageInfo<OrderEntity> pageInfo) {

    Pager pager = new Pager();
    pager.setIsFirstPage(pageInfo.isIsFirstPage());
    pager.setIsLastPage(pageInfo.isIsLastPage());
    pager.setTotal(pageInfo.getTotal());
    pager.setPageNum(pageInfo.getPageNum());
    pager.setPageSize(pageInfo.getPageSize());
    pager.setSize(pageInfo.getSize());
    pager.setPages(pageInfo.getPages());

    return pager;
  }

  public List<Order> mapOrders(PageInfo<OrderEntity> pageInfo) {

    List<Order> orders = new ArrayList<>();
    pageInfo.getList().stream()
        .forEach(
            o -> {
              Order order = new Order();
              order.setId(o.getId());
              order.setId(o.getId());
              order.setPetId(o.getPetId());
              order.setUserId(o.getUserId());
              order.setShipDate(o.getShipDate());
              order.setStatus(OrderStatus.fromValue(o.getStatus()));
              order.setComplete(o.isComplete());

              orders.add(order);
            });
    return orders;
  }
}
