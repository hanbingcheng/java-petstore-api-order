package com.example.petstore.api.order.domain.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetOrderByIdServiceInput {
  private long orderId;
}
