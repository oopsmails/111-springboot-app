package com.oopsmails.springboot.mockbackend.employee.service;

import com.oopsmails.springboot.mockbackend.employee.model.Employee;
import com.oopsmails.springboot.mockbackend.employee.repository.EmployeeRepository;
import com.oopsmails.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EmployeeService {
    @Autowired
    EmployeeRepository repository;

    public Page<Employee> getPaginatedEmployees(int pageNumber, int pageSize) {
        List<Employee> employees = repository.findAll();
        log.info(JsonUtil.objectToJsonString(employees, true));
        int startIndex = (pageNumber - 1) * pageSize;
        int endIndex = startIndex + pageSize;
        if (endIndex > employees.size()) {
            endIndex = employees.size();
        }
        List<Employee> paginatedEmployees = employees.subList(startIndex, endIndex);
        Page<Employee> employeePage = new PageImpl<>(paginatedEmployees, PageRequest.of(pageNumber - 1, pageSize), employees.size());
        return employeePage;
    }
}
