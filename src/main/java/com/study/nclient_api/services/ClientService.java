package com.study.nclient_api.services;

import com.study.nclient_api.entities.Client;
import com.study.nclient_api.repositories.ClientRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

    private ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Transactional(readOnly = true)
    public Page<Client> getAllClients(Pageable pageable) {
        return clientRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Client getClientById(Long id) {
        return clientRepository.findById(id).get();
    }

    @Transactional
    public Client insertClient(Client client) {
        return clientRepository.save(client);
    }

    @Transactional
    public Client updateClient(Long id, Client client) {
        Client entity = clientRepository.getReferenceById(id);
        BeanUtils.copyProperties(client, entity,"id");
        clientRepository.save(client);
        return entity;
    }
}
