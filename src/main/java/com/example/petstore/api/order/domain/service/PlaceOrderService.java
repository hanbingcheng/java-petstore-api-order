package com.example.petstore.api.order.domain.service;

import com.example.petstore.api.order.domain.model.OrderEntity;
import com.example.petstore.api.order.domain.model.PetStatus;
import com.example.petstore.api.order.domain.repository.OrderRepository;
import com.example.petstore.api.order.domain.repository.PetRepository;
import com.example.petstore.api.order.domain.service.dto.PlaceOrderServiceInput;
import com.example.petstore.api.order.domain.service.dto.PlaceOrderServiceOutput;
import com.example.petstore.api.order.oas.model.OrderStatus;
import com.example.petstore.common.api.errorhandler.constant.CommonErrorCode;
import com.example.petstore.common.api.errorhandler.exception.AppValidationException;
import com.example.petstore.common.core.base.exception.SystemException;
import com.example.petstore.common.core.base.logging.AppLogger;
import com.example.petstore.common.core.base.logging.annotation.StartEndLog;
import com.example.petstore.common.core.base.logging.constant.CommonLogId;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PlaceOrderService {

  private final AppLogger logger;
  private final OrderRepository orderRepository;
  private final PetRepository petRepository;

  @StartEndLog
  @Transactional
  public PlaceOrderServiceOutput execute(PlaceOrderServiceInput input) {

    if (!checkPetExists(input.getPetId())) {
      throw new AppValidationException(
          CommonErrorCode.REQUEST_PARAMETER_ERROR, "pet_id", input.getPetId(), "invalid pet id");
    }
    updatePetStatus(input.getPetId());
    long id = insertOrder(input);
    return PlaceOrderServiceOutput.builder().orderId(id).build();
  }

  private boolean checkPetExists(long petId) {
    try {
      return petRepository.checkPetExists(petId);
    } catch (DataAccessException ex) {
      logger.error(CommonLogId.DB_ACCESS_ERROR, ex, "PetRepository", "checkPetExists");
      throw new SystemException(CommonErrorCode.DBACCESS_ERROR);
    }
  }

  private void updatePetStatus(long petId) {
    try {
      petRepository.updateStatus(petId, PetStatus.PENDING.getValue());
    } catch (DataAccessException ex) {
      logger.error(CommonLogId.DB_ACCESS_ERROR, ex, "PetRepository", "updateStatus");
      throw new SystemException(CommonErrorCode.DBACCESS_ERROR);
    }
  }

  private long insertOrder(PlaceOrderServiceInput input) {
    OrderEntity order =
        OrderEntity.builder()
            .petId(input.getPetId())
            .userId(input.getUserId())
            .status(OrderStatus.PLACED.getValue())
            .build();
    try {
      orderRepository.insert(order);
    } catch (DataAccessException ex) {
      logger.error(CommonLogId.DB_ACCESS_ERROR, ex, "OrderRepository", "insert");
      throw new SystemException(CommonErrorCode.DBACCESS_ERROR);
    }
    return order.getId();
  }
}
