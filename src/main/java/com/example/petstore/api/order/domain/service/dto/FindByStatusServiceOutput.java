package com.example.petstore.api.order.domain.service.dto;

import com.example.petstore.api.order.domain.model.OrderEntity;
import com.github.pagehelper.PageInfo;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FindByStatusServiceOutput {
  private PageInfo<OrderEntity> pageInfo;
}
