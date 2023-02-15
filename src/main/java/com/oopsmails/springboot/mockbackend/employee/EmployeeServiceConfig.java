package com.oopsmails.springboot.mockbackend.employee;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oopsmails.springboot.mockbackend.employee.model.Employee;
import com.oopsmails.springboot.mockbackend.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Configuration
public class EmployeeServiceConfig {
    @Autowired
    private ResourceLoader resourceLoader;

    @Bean
    EmployeeRepository repository() {
        EmployeeRepository repository = new EmployeeRepository();

        Resource resource = resourceLoader.getResource("classpath:mockdata/employees.json");
        try {
            String filePath = resource.getURI().getPath();
            InputStream inputStream = resource.getInputStream();
            ObjectMapper objectMapper = new ObjectMapper();
            List<Employee> employees = objectMapper.readValue(inputStream, new TypeReference<List<Employee>>() {
            });
            employees.forEach((employee -> repository.add(employee)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //        repository.add(new Employee(1L, 1L, "John Smith", 34, "Analyst"));
        //        repository.add(new Employee(1L, 1L, "Darren Hamilton", 37, "Manager"));
        //        repository.add(new Employee(1L, 1L, "Tom Scott", 26, "Developer"));
        //        repository.add(new Employee(1L, 2L, "Anna London", 39, "Analyst"));
        //        repository.add(new Employee(1L, 2L, "Patrick Dempsey", 27, "Developer"));
        //        repository.add(new Employee(2L, 3L, "Kevin Price", 38, "Developer"));
        //        repository.add(new Employee(2L, 3L, "Ian Scott", 34, "Developer"));
        //        repository.add(new Employee(2L, 3L, "Andrew Campton", 30, "Manager"));
        //        repository.add(new Employee(2L, 4L, "Steve Franklin", 25, "Developer"));
        //        repository.add(new Employee(2L, 4L, "Elisabeth Smith", 30, "Developer"));
        //
        //
        //        for (int i = 1; i <= 10; i++) {
        //            Long id = (long) i;
        //            Long organizationId = (long) (Math.random() * 3) + 1;
        //            Long departmentId = (long) (Math.random() * 6) + 1;
        //            String name = "Employee " + i;
        //            int age = (int) (Math.random() * 40) + 20;
        //            String position = "Position " + i;
        //
        //            Employee employee = new Employee(organizationId, departmentId, name, age, position);
        //            repository.add(employee);
        //        }

        return repository;
    }
}
