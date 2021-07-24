package com.allenayo.sbj.service;

import com.allenayo.sbj.dao.EmployeeRepository;
import com.allenayo.sbj.dao.UserRepository;
import com.allenayo.sbj.domain.Employee;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeDao;

    public EmployeeService(EmployeeRepository employeeDao) {
        this.employeeDao = employeeDao;
    }

    public void save(Employee employee) {
        employeeDao.save(employee);
    }

    public void update(Employee employee) {
        employeeDao.save(employee);
    }

    public Employee findById(long id) {
        return employeeDao.findById(id).orElse(null);
    }

    public void deleteById(long id) {
        employeeDao.deleteById(id);
    }
}