package com.spring.reactive.db.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@Document
public class Employee {

    @Id
    private String id;
    private String name;
    private Long salary;

}
