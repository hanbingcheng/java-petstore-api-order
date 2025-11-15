package com.example.petstore.api.order.domain.service.dto;

import com.example.petstore.api.order.domain.model.OrderEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetOrderByIdServiceOutput {
  private OrderEntity order;
}
