package com.allenayo.sbj.dao;

import com.allenayo.sbj.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}