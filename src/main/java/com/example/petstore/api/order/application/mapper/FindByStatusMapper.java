package com.example.petstore.api.order.application.mapper;

import com.example.petstore.api.order.domain.service.dto.FindByStatusServiceInput;
import com.example.petstore.api.order.domain.service.dto.FindByStatusServiceOutput;
import com.example.petstore.api.order.oas.model.FindOrdersByStatusResponseBody;
import com.example.petstore.api.order.oas.model.OrderStatus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FindByStatusMapper {

  private final CommonMapper commonMapper;

  public FindByStatusServiceInput map(
      OrderStatus status, Integer pageNum, Integer pageSize, Long userId) {
    return FindByStatusServiceInput.builder()
        .status(status)
        .pageNum(pageNum)
        .pageSize(pageSize)
        .userId(userId)
        .build();
  }

  public FindOrdersByStatusResponseBody map(FindByStatusServiceOutput output) {

    FindOrdersByStatusResponseBody responseBody = new FindOrdersByStatusResponseBody();
    responseBody.setPager(commonMapper.mapPager(output.getPageInfo()));
    responseBody.setList(commonMapper.mapOrders(output.getPageInfo()));
    return responseBody;
  }
}
