package com.example.petstore.api.order.application.mapper;

import com.example.petstore.api.order.domain.model.OrderEntity;
import com.example.petstore.api.order.domain.service.dto.GetOrderByIdServiceInput;
import com.example.petstore.api.order.domain.service.dto.GetOrderByIdServiceOutput;
import com.example.petstore.api.order.oas.model.OrderDetailResponseBody;
import com.example.petstore.api.order.oas.model.OrderStatus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GetOrderByIdMapper {

  public GetOrderByIdServiceInput map(long orderId) {
    return GetOrderByIdServiceInput.builder().orderId(orderId).build();
  }

  public OrderDetailResponseBody map(GetOrderByIdServiceOutput output) {

    OrderEntity o = output.getOrder();
    OrderDetailResponseBody order = new OrderDetailResponseBody();
    order.setId(o.getId());
    order.setPetId(o.getPetId());
    order.setUserId(o.getUserId());
    order.setShipDate(o.getShipDate());
    order.setStatus(OrderStatus.fromValue(o.getStatus()));
    order.setComplete(o.isComplete());
    order.setUserName(o.getUserName());
    order.setPetName(o.getPetName());

    return order;
  }
}
