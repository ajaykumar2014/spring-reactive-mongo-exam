package com.spring.reactive;

import com.spring.reactive.db.model.Employee;
import com.spring.reactive.repo.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class SpringReactiveMongoApplication {

    @Bean
    CommandLineRunner commandLineRunner(EmployeeRepository employeeRepository) {
        return args -> {
            employeeRepository.deleteAll().subscribe(null, null, () -> {

                Stream.of(new Employee(UUID.randomUUID().toString(), "Ajay", 500000L), new Employee(UUID.randomUUID().toString(), "Rahul", 1500000L),
                        new Employee(UUID.randomUUID().toString(), "Wasim", 5200000L), new Employee(UUID.randomUUID().toString(), "Santosh", 2100000L))
                        .forEach(employee -> employeeRepository.save(employee).subscribe(employee1 -> System.out.println(employee1)));

            });
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringReactiveMongoApplication.class, args);
    }

}
