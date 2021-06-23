package com.world_of_anonymous.webflux_demo.controller;

import com.world_of_anonymous.webflux_demo.domain.Employee;
import com.world_of_anonymous.webflux_demo.repositories.EmployeeRepository;
import com.world_of_anonymous.webflux_demo.service.EmployeeService;
import jdk.jfr.ContentType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = EmployeeController.class)
@Import(EmployeeService.class)
public class EmployeeControllerTest {

  @Autowired
  private WebTestClient webTestClient;

  @MockBean
  private EmployeeRepository employeeRepository;

  @Test
  public void createEmployee() {
    Employee employee = new Employee();
    employee.setId(1);
    employee.setName("Test");
    employee.setSalary(1000);

    Mockito.when(employeeRepository.save(employee)).thenReturn(Mono.just(employee));

    webTestClient.post()
        .uri("/create")
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromObject(employee))
        .exchange()
        .expectStatus().isCreated();

    Mockito.verify(employeeRepository, Mockito.times(1)).save(employee);
  }

  @Test
  public void testGetEmployeeByName() {
    Employee employee = new Employee();
    employee.setId(1);
    employee.setName("Test");
    employee.setSalary(1000);

    Flux<Employee> employeeFlux = Flux.fromIterable(List.of(employee));

    Mockito.when(employeeRepository.findByName(employee.getName())).thenReturn(employeeFlux);

    webTestClient.get()
        .uri("/name/{name}", "Test")
        .header(HttpHeaders.ACCEPT, "application/json")
        .exchange()
        .expectStatus().isOk()
        .expectBodyList(Employee.class);

    Mockito.verify(employeeRepository, Mockito.times(1)).findByName("Test");
  }

  @Test
  public void testGetEmployeeById() {
    Employee employee = new Employee();
    employee.setId(100);
    employee.setName("Test");
    employee.setSalary(1000);

    Mockito.when(employeeRepository.findById(100)).thenReturn(Mono.just(employee));

    webTestClient.get()
        .uri("/{id}", 100)
        .exchange()
        .expectStatus().isOk()
        .expectBody()
        .jsonPath("$.name").isNotEmpty()
        .jsonPath("$.id").isEqualTo(100)
        .jsonPath("$.name").isEqualTo("Test")
        .jsonPath("$.salary").isEqualTo(1000);
  }

  @Test
  public void deleteEmployee() {
    Mono<Void> voidReturn = Mono.empty();
    Mockito.when(employeeRepository.deleteById(1)).thenReturn(voidReturn);

    webTestClient.delete()
        .uri("/delete/{id}", 1)
        .exchange()
        .expectStatus().isOk();
  }
}
