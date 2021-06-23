package com.world_of_anonymous.webflux_demo.repositories;

import com.world_of_anonymous.webflux_demo.domain.Employee;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

public interface EmployeeRepository extends ReactiveMongoRepository<Employee, Integer> {
  @Query("{ 'name':?0 }")
  Flux<Employee> findByName(final String name);
}
