package com.world_of_anonymous.webflux_webclient.service;

import com.world_of_anonymous.webflux_webclient.model.Employee;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IEmployeeService {
  Mono<Employee> create(Employee emp);
  Mono<Employee> update(Employee emp);
  Mono<Void> delete(Integer emp);
  Mono<Employee> findById(Integer id);
  Flux<Employee> findAll();
}
