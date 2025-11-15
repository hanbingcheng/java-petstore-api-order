package com.example.petstore.api.order.domain.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlaceOrderServiceInput {
  private Long petId;
  private Long userId;
}
