package io.abdul.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Person extends PanacheEntity {

    private String name;
    private LocalDate birth;
    private Status status;
}