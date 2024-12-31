package com.study.nclient_api.controllers;

import com.study.nclient_api.dto.ClientDTO;
import com.study.nclient_api.entities.Client;
import com.study.nclient_api.mappers.ClientMapper;
import com.study.nclient_api.services.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {

    private ClientService clientService;
    private ClientMapper clientMapper;

    public ClientController(ClientService clientService, ClientMapper clientMapper) {
        this.clientService = clientService;
        this.clientMapper = clientMapper;
    }

    @GetMapping
    public ResponseEntity<List<ClientDTO>> getAllClients() {
        List<ClientDTO> clients = clientService.getAllClients()
                .stream().map(clientMapper::clientToClientDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(clients);
    }
}
