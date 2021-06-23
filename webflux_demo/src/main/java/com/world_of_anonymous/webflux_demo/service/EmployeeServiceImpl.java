package com.world_of_anonymous.webflux_demo.service;

import com.world_of_anonymous.webflux_demo.domain.Employee;
import com.world_of_anonymous.webflux_demo.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EmployeeServiceImpl implements EmployeeService {

  @Autowired
  EmployeeRepository empRepository;

  public void create(Employee emp) {
    empRepository.save(emp).subscribe();
  }

  public Mono<Employee> findById(Integer id) {
    return empRepository.findById(id);
  }

  public Flux<Employee> findByName(String name) {
    return empRepository.findByName(name);
  }

  public Flux<Employee> findAll() {
    return empRepository.findAll();
  }

  public Mono<Employee> update(Employee emp) {
    return empRepository.save(emp);
  }

  public Mono<Void> delete(Integer id) {
    return empRepository.deleteById(id);
  }
}
