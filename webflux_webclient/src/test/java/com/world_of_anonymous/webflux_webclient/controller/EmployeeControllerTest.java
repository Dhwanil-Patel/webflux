package com.world_of_anonymous.webflux_webclient.controller;

import com.world_of_anonymous.webflux_webclient.model.Employee;
import com.world_of_anonymous.webflux_webclient.repository.EmployeeRepository;
import com.world_of_anonymous.webflux_webclient.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@WebFluxTest
@Import(EmployeeService.class)
public class EmployeeControllerTest {

  @Autowired
  private WebTestClient webTestClient;

  @MockBean
  private EmployeeRepository employeeRepository;

  @Test
  @Deprecated
  void testCreateEmployee() {
    Employee emp = new Employee();
    emp.setId(1);
    emp.setName("Test");
    emp.setStatus("1000");

//    Mockito.when(employeeRepository.save(emp)).thenReturn(Mono.just(emp));
//
//    webTestClient.post()
//        .uri("/create")
//        .contentType(MediaType.APPLICATION_JSON)
//        .body(BodyInserters.fromObject(emp))
//        .exchange()
//        .expectStatus().isCreated();
//
//    Mockito.verify(employeeRepository, Mockito.times(1)).save(emp)
  }
}
