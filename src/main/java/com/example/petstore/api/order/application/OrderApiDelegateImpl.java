package com.example.petstore.api.order.application;

import com.example.petstore.api.order.application.mapper.FindByStatusMapper;
import com.example.petstore.api.order.application.mapper.GetOrderByIdMapper;
import com.example.petstore.api.order.application.mapper.PlaceOrderMapper;
import com.example.petstore.api.order.application.mapper.UpdateOrderMapper;
import com.example.petstore.api.order.domain.service.FindByStatusService;
import com.example.petstore.api.order.domain.service.GetOrderByIdService;
import com.example.petstore.api.order.domain.service.PlaceOrderService;
import com.example.petstore.api.order.domain.service.UpdateOrderService;
import com.example.petstore.api.order.domain.service.dto.*;
import com.example.petstore.api.order.oas.api.OrderApiDelegate;
import com.example.petstore.api.order.oas.model.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class OrderApiDelegateImpl implements OrderApiDelegate {

  private final FindByStatusMapper findByStatusMapper;
  private final FindByStatusService findByStatusService;

  private final PlaceOrderMapper placeOrderMapper;
  private final PlaceOrderService placeOrderService;

  private final UpdateOrderMapper updateOrderMapper;
  private final UpdateOrderService updateOrderService;

  private final GetOrderByIdMapper getOrderByIdMapper;
  private final GetOrderByIdService getOrderByIdService;

  @Override
  public ResponseEntity<FindOrdersByStatusResponseBody> findOrderssByStatus(
      OrderStatus status, Integer pageNum, Integer pageSize, Long xUserId) {
    FindByStatusServiceInput input = findByStatusMapper.map(status, pageNum, pageSize, xUserId);
    FindByStatusServiceOutput output = findByStatusService.execute(input);
    FindOrdersByStatusResponseBody responseBody = findByStatusMapper.map(output);
    return new ResponseEntity<>(responseBody, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<PlaceOrderResponseBody> placeOrder(
      PlaceOrderRequestBody placeOrderRequestBody) {
    PlaceOrderServiceInput input = placeOrderMapper.map(placeOrderRequestBody);
    PlaceOrderServiceOutput output = placeOrderService.execute(input);
    PlaceOrderResponseBody responseBody = placeOrderMapper.map(output);
    return new ResponseEntity<>(responseBody, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<OrderDetailResponseBody> getOrderById(Long id) {
    GetOrderByIdServiceInput input = getOrderByIdMapper.map(id);
    GetOrderByIdServiceOutput output = getOrderByIdService.execute(input);
    OrderDetailResponseBody responseBody = getOrderByIdMapper.map(output);
    return new ResponseEntity<>(responseBody, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Void> updateOrder(Long id, UpdateOrderRequestBody updateOrderRequestBody) {
    UpdateOrderServiceInput input = updateOrderMapper.map(id, updateOrderRequestBody);
    updateOrderService.execute(input);
    return new ResponseEntity<>(null, HttpStatus.OK);
  }
}
