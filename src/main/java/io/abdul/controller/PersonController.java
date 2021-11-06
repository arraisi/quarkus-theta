package io.abdul.controller;

import io.abdul.model.Person;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/api/person")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonController {

    @POST
    @Transactional
    public Response save(Person person) {
        person.persist();
        if (person.isPersistent()) {
            return Response.created(URI.create("/api/person/" + person.id)).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response deleteById(@PathParam("id") Long id) {
        boolean deleted = Person.deleteById(id);
        if (deleted) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @GET
    @Path("/{id}")
    @Transactional
    public Response personById(@PathParam("id") Long id) {
        return Person.findByIdOptional(id)
                .map(person -> Response.ok(person).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @GET
    public Response list() {
        List<Person> personList = Person.listAll();
        return Response.ok(personList).build();
    }

    @GET
    @Path("/address/{address}")
    public Response personByAddress(@PathParam("address") String address) {
        List<Person> personList = Person.list("SELECT m FROM Person m WHERE m.address = ?1 ORDER BY id DESC", address);
        return Response.ok(personList).build();
    }

    @GET
    @Path("/name/{name}")
    public Response personByName(@PathParam("name") String name) {
        return Person.find("name", name)
                .singleResultOptional()
                .map(person -> Response.ok(person).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }
}
