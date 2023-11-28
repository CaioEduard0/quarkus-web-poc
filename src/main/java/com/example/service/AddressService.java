package com.example.service;

import com.example.client.ViaCepClient;
import com.example.entity.Address;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Slf4j
@ApplicationScoped
public class AddressService {

    private final ViaCepClient viaCepClient;
    private final Emitter<String> emitter;

    public AddressService(@RestClient final ViaCepClient viaCepClient, @Channel("address-out") final Emitter<String> emitter) {
        this.viaCepClient = viaCepClient;
        this.emitter = emitter;
    }

    @Transactional
    public Address getAddress(final String cep) {

        final Address address = viaCepClient.fetchAddress(cep);
        emitter.send(address.toString());
        address.persist();
        return address;
    }

    @Incoming("address-in")
    public void receive(final String message) {
        log.info("Consumer received message: {}", message);
    }
}