package io.abdul.controller;

import io.abdul.model.Person;
import io.abdul.model.Status;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.time.Month;

@Path("/api/person")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonController {

    @POST
    @Transactional
    public Response save() {
        // creating a person
        Person person = new Person();
        person.name = "Stef";
        person.birth = LocalDate.of(1910, Month.FEBRUARY, 1);
        person.status = Status.ACTIVE;

        // persist it
        person.persist();
        // note that once persisted, you don't need to explicitly save your entity: all
        // modifications are automatically persisted on transaction commit.
        return Response.ok(person).build();
    }

    @GET
    @Path("/{id}")
    @Transactional
    public Response findById(@PathParam("id") Long id) {
        // finding a specific person by ID
        Person person = Person.findById(id);

        // check if it's persistent
        if (person.isPersistent()) {
            // delete it
            person.delete();
        }
        return Response.ok(person).build();
    }
}
