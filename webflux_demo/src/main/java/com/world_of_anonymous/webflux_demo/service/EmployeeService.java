package com.world_of_anonymous.webflux_demo.service;

import com.world_of_anonymous.webflux_demo.domain.Employee;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface  EmployeeService {
  void create(Employee emp);
  Mono<Employee> findById(Integer id);
  Flux<Employee> findByName(String name);
  Flux<Employee> findAll();
  Mono<Employee> update(Employee emp);
  Mono<Void> delete(Integer id);
}
