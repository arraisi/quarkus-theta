package io.abdul.controller;

import io.abdul.model.Person;
import io.abdul.repository.PersonRepository;
import io.quarkus.hibernate.orm.panache.PanacheQuery;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/hello")
public class GreetingResource {

    @Inject
    public PersonRepository personRepository;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        List<Person> people = personRepository.listAll();
        if (!people.isEmpty()) {
            return "error";
        }
        return "Hello RESTEasy";
    }
}