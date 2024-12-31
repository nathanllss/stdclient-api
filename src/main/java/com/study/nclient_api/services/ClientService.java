package com.study.nclient_api.services;

import com.study.nclient_api.entities.Client;
import com.study.nclient_api.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAllClients() {
        List<Client> clients = clientRepository.findAll();
        return clients;
    }
}
