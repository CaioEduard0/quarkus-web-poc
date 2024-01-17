package com.example.client;

import com.example.entity.Address;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "viacep")
public interface ViaCepClient {

    @GET
    @Path("{cep}")
    Address fetchAddress(@PathParam("cep") final String cep);
}