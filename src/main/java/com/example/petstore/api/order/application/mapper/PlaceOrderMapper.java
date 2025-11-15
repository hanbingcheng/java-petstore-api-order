package com.example.petstore.api.order.application.mapper;

import com.example.petstore.api.order.domain.service.dto.PlaceOrderServiceInput;
import com.example.petstore.api.order.domain.service.dto.PlaceOrderServiceOutput;
import com.example.petstore.api.order.oas.model.PlaceOrderRequestBody;
import com.example.petstore.api.order.oas.model.PlaceOrderResponseBody;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PlaceOrderMapper {
  public PlaceOrderServiceInput map(PlaceOrderRequestBody requestBody) {

    return PlaceOrderServiceInput.builder()
        .petId(requestBody.getPetId())
        .userId(requestBody.getUserId())
        .build();
  }

  public PlaceOrderResponseBody map(PlaceOrderServiceOutput output) {
    PlaceOrderResponseBody body = new PlaceOrderResponseBody();
    body.setId(output.getOrderId());
    return body;
  }
}
