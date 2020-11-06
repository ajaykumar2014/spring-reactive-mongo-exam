package com.spring.reactive.db.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeEvent {

    private Employee employee;
    private LocalDateTime localDateTime;

}
