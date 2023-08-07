package com.example.quartsdemo.batch.repository;

import com.example.quartsdemo.batch.repository.entity.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoffeeRepository extends JpaRepository<Coffee, Long> {

}
