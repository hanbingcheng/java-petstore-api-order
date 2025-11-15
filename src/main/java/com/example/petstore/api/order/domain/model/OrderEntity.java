package com.example.petstore.api.order.domain.model;

import java.time.LocalDateTime;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {
  private Long id;
  private Long petId;
  private long userId;
  private LocalDateTime shipDate;
  private String status; // placed, approved, delivered
  private boolean complete;

  private String petName;
  private String userName;
}
