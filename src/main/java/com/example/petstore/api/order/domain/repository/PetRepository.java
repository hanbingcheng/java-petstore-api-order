package com.example.petstore.api.order.domain.repository;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PetRepository {

  boolean checkPetExists(long petId);

  void updateStatus(long id, String status);
}
