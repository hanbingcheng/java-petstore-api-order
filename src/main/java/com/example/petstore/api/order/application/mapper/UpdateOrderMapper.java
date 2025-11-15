package com.example.petstore.api.order.application.mapper;

import com.example.petstore.api.order.domain.service.dto.UpdateOrderServiceInput;
import com.example.petstore.api.order.oas.model.UpdateOrderRequestBody;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UpdateOrderMapper {

  public UpdateOrderServiceInput map(Long petId, UpdateOrderRequestBody requestBody) {

    return UpdateOrderServiceInput.builder()
        .id(petId)
        .shipDate(requestBody.getShipDate())
        .status(requestBody.getStatus())
        .complete(requestBody.getComplete())
        .build();
  }
}
