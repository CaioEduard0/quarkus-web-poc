package com.example.resource;

import com.example.service.AddressService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;

@Path("addresses")
@ApplicationScoped
@RequiredArgsConstructor
public class AddressResource {

    private final AddressService addressService;

    @GET
    @Path("{cep}")
    public Response getAddress(@PathParam("cep") final String cep) {
        return Response.ok(addressService.getAddress(cep)).build();
    }
}