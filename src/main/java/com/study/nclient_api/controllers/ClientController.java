package com.study.nclient_api.controllers;

import com.study.nclient_api.dto.ClientDTO;
import com.study.nclient_api.entities.Client;
import com.study.nclient_api.mappers.ClientMapper;
import com.study.nclient_api.services.ClientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Page<ClientDTO>> getAllClients(Pageable pageable) {
        Page<ClientDTO> result = clientService.getAllClients(pageable)
                .map(clientMapper::clientToClientDTO);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable Long id) {
        ClientDTO result = clientMapper.clientToClientDTO(clientService.getClientById(id));
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<ClientDTO> insertClient(@RequestBody ClientDTO clientDTO) {
        Client client = clientService.insertClient(clientMapper.clientDTOToClient(clientDTO));
        ClientDTO result = clientMapper.clientToClientDTO(client);
        return ResponseEntity.ok(result);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> updateClient(@PathVariable Long id, @RequestBody ClientDTO clientDTO) {
        Client client = clientMapper.clientDTOToClient(clientDTO);
        client = clientService.updateClient(id, client);
        ClientDTO result = clientMapper.clientToClientDTO(client);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.deleteClientById(id);
        return ResponseEntity.noContent().build();
    }
}
