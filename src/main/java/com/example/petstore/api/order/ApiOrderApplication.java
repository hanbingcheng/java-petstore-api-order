package com.example.petstore.api.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@SpringBootApplication
public class ApiOrderApplication {
  @Autowired private RequestMappingHandlerMapping handlerMapping;

  public static void main(String[] args) {
    SpringApplication.run(ApiOrderApplication.class, args);
  }
}
