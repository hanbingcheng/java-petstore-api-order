package com.example.petstore.api.order.domain.service;

import com.example.petstore.api.order.domain.model.OrderEntity;
import com.example.petstore.api.order.domain.repository.OrderRepository;
import com.example.petstore.api.order.domain.service.dto.FindByStatusServiceInput;
import com.example.petstore.api.order.domain.service.dto.FindByStatusServiceOutput;
import com.example.petstore.common.api.errorhandler.constant.CommonErrorCode;
import com.example.petstore.common.core.base.exception.SystemException;
import com.example.petstore.common.core.base.logging.AppLogger;
import com.example.petstore.common.core.base.logging.annotation.StartEndLog;
import com.example.petstore.common.core.base.logging.constant.CommonLogId;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FindByStatusService {

  private final AppLogger logger;
  private final OrderRepository orderRepository;

  @StartEndLog
  public FindByStatusServiceOutput execute(FindByStatusServiceInput input) {
    PageHelper.startPage(input.getPageNum(), input.getPageSize());
    List<OrderEntity> orders;
    try {
      orders = orderRepository.findByStatus(input.getStatus().getValue(), input.getUserId());
    } catch (DataAccessException ex) {
      logger.error(CommonLogId.DB_ACCESS_ERROR, ex, "OrderRepository", "findByStatus");
      throw new SystemException(CommonErrorCode.DBACCESS_ERROR);
    }
    PageInfo<OrderEntity> pageInfo = new PageInfo<>(orders);
    return FindByStatusServiceOutput.builder().pageInfo(pageInfo).build();
  }
}
