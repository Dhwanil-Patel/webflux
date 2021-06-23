package com.world_of_anonymous.webflux_webclient.controller;

import com.world_of_anonymous.webflux_webclient.model.Employee;
import com.world_of_anonymous.webflux_webclient.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

  @Autowired
  private EmployeeService employeeService;

  @PostMapping(path = "/", consumes = "application/json", produces = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<Employee> create(@RequestBody Employee emp) {
    return employeeService.create(emp);
  }

  @PutMapping(path = "/{id}", produces = "application/json")
  @ResponseStatus(HttpStatus.OK)
  public Mono<Employee> update(@RequestBody Employee emp, @PathVariable("id") Integer id) {
    emp.setId(id);
    return employeeService.update(emp);
  }

  @DeleteMapping(path = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Mono<Void> delete(@PathVariable("id") Integer id) {
    return employeeService.delete(id);
  }

  @GetMapping(path = "/{id}", produces = "application/json")
  public Mono<Employee> findById(@PathVariable("id") Integer id) {
    return employeeService.findById(id);
  }

  @GetMapping(path = "/", produces = "application/json")
  @ResponseStatus(HttpStatus.OK)
  public Flux<Employee> findAll() {
    return employeeService.findAll();
  }
}
