package com.example.petstore.api.order.domain.service;

import com.example.petstore.api.order.domain.model.OrderEntity;
import com.example.petstore.api.order.domain.repository.OrderRepository;
import com.example.petstore.api.order.domain.service.dto.GetOrderByIdServiceInput;
import com.example.petstore.api.order.domain.service.dto.GetOrderByIdServiceOutput;
import com.example.petstore.common.api.errorhandler.constant.CommonErrorCode;
import com.example.petstore.common.core.base.exception.SystemException;
import com.example.petstore.common.core.base.logging.AppLogger;
import com.example.petstore.common.core.base.logging.annotation.StartEndLog;
import com.example.petstore.common.core.base.logging.constant.CommonLogId;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetOrderByIdService {

  private final AppLogger logger;
  private final OrderRepository orderRepository;

  @StartEndLog
  public GetOrderByIdServiceOutput execute(GetOrderByIdServiceInput input) {

    OrderEntity order;
    try {
      order = orderRepository.getOrderById(input.getOrderId());
    } catch (DataAccessException ex) {
      logger.error(CommonLogId.DB_ACCESS_ERROR, ex, "OrderRepository", "getOrderById");
      throw new SystemException(CommonErrorCode.DBACCESS_ERROR);
    }
    return GetOrderByIdServiceOutput.builder().order(order).build();
  }
}
