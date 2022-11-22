package com.dahit.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dahit.demo.entity.Employee;
@Repository
public interface repository extends JpaRepository<Employee,Long> {

    
}
