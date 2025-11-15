package com.example.petstore.api.order.domain.service.dto;

import com.example.petstore.api.order.oas.model.OrderStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FindByStatusServiceInput {
  private OrderStatus status;
  private int pageNum;
  private int pageSize;
  private Long userId;
}
