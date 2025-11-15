package com.example.petstore.api.order.domain.service.dto;

import com.example.petstore.api.order.oas.model.OrderStatus;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateOrderServiceInput {
  private Long id;
  private Long petId;
  private LocalDateTime shipDate;
  private OrderStatus status;
  private boolean complete;
}
