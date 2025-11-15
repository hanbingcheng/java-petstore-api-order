package com.example.petstore.api.order.domain.service;

import com.example.petstore.api.order.domain.model.OrderEntity;
import com.example.petstore.api.order.domain.model.PetStatus;
import com.example.petstore.api.order.domain.repository.OrderRepository;
import com.example.petstore.api.order.domain.repository.PetRepository;
import com.example.petstore.api.order.domain.service.dto.UpdateOrderServiceInput;
import com.example.petstore.common.api.errorhandler.constant.CommonErrorCode;
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
public class UpdateOrderService {

  private final AppLogger logger;
  private final OrderRepository orderRepository;
  private final PetRepository petRepository;

  @StartEndLog
  @Transactional
  public void execute(UpdateOrderServiceInput input) {

    updateOrder(input);
    // 取引完了の場合、ペットのステータスをSOLDに変更
    if (input.isComplete()) {
      updatePetStatus(input.getPetId());
    }
  }

  private void updateOrder(UpdateOrderServiceInput input) {
    OrderEntity order =
        OrderEntity.builder()
            .id(input.getId())
            .shipDate(input.getShipDate())
            .status(input.getStatus().getValue())
            .complete(input.isComplete())
            .build();
    try {
      orderRepository.update(order);
    } catch (DataAccessException ex) {
      logger.error(CommonLogId.DB_ACCESS_ERROR, ex, "OrderRepository", "update");
      throw new SystemException(CommonErrorCode.DBACCESS_ERROR);
    }
  }

  private void updatePetStatus(long petId) {
    try {
      petRepository.updateStatus(petId, PetStatus.SOLD.getValue());
    } catch (DataAccessException ex) {
      logger.error(CommonLogId.DB_ACCESS_ERROR, ex, "PetRepository", "updateStatus");
      throw new SystemException(CommonErrorCode.DBACCESS_ERROR);
    }
  }
}
