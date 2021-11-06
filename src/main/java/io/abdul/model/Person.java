package io.abdul.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Person extends PanacheEntity {
    public String name;
    public String address;
    public LocalDate birth;
    public Status status;

    @OneToMany(mappedBy = "person", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JsonIgnoreProperties("person")
    public List<Role> roles = new ArrayList<>();
}