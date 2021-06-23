package com.world_of_anonymous.webflux_webclient.service;

import com.world_of_anonymous.webflux_webclient.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class EmployeeService implements IEmployeeService {

  @Autowired
  WebClient webClient;

  @Override
  public Mono<Employee> create(Employee emp) {
    return webClient.post()
        .uri("/employees")
        .body(Mono.just(emp), Employee.class)
        .retrieve()
        .bodyToMono(Employee.class)
        .timeout(Duration.ofMillis(10_000));
  }

  @Override
  public Mono<Employee> update(Employee emp) {
    return webClient.put()
        .uri("/employees/" + emp.getId())
        .body(Mono.just(emp), Employee.class)
        .retrieve()
        .bodyToMono(Employee.class);
  }

  @Override
  public Mono<Void> delete(Integer id) {
    return webClient.delete()
        .uri("/employees" + id)
        .retrieve()
        .bodyToMono(Void.class);
  }

  @Override
  public Mono<Employee> findById(Integer id) {
    return webClient.get()
        .uri("/employees" + id)
        .retrieve()
        .bodyToMono(Employee.class);
  }

  @Override
  public Flux<Employee> findAll() {
    return webClient.get()
        .uri("/employees")
        .retrieve()
        .bodyToFlux(Employee.class)
        .timeout(Duration.ofMillis(10_000));
  }
}
