package com.spring.reactive.resources;

import com.spring.reactive.db.model.Employee;
import com.spring.reactive.db.model.EmployeeEvent;
import com.spring.reactive.repo.EmployeeRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.awt.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.stream.Stream;

@RestController
@RequestMapping("/rest/employee")
public class EmployeeResource {

    private EmployeeRepository employeeRepository;

    public EmployeeResource(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @GetMapping("/all")
    public Flux<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    @GetMapping("/{Id}")
    public Mono getEmployee(@PathVariable("Id") String id){
        return employeeRepository.findById(id);
    }

    @GetMapping(value = "/{Id}/events",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<EmployeeEvent> getEmployeeStream(@PathVariable("Id") String id){
        return employeeRepository.findById(id).flatMapMany(employee -> {
            Flux<Long> fluxInterval = Flux.interval(Duration.ofSeconds(2));
            Flux<EmployeeEvent> fluxEmployeeEvent = Flux.fromStream(Stream.generate((()->new EmployeeEvent(employee, LocalDateTime.now()))));
            return Flux.zip(fluxInterval,fluxEmployeeEvent).map(Tuple2::getT2);
        });
    }

}
