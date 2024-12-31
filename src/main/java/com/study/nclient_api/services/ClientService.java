package com.study.nclient_api.services;

import com.study.nclient_api.entities.Client;
import com.study.nclient_api.repositories.ClientRepository;
import com.study.nclient_api.services.exceptions.DatabaseException;
import com.study.nclient_api.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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
        return clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
    }

    @Transactional
    public Client insertClient(Client client) {
        return clientRepository.save(client);
    }

    @Transactional
    public Client updateClient(Long id, Client client) {
        try {
            Client entity = clientRepository.getReferenceById(id);
            BeanUtils.copyProperties(client, entity, "id");
            clientRepository.save(client);
            return entity;
        } catch (Exception e) {
            throw new ResourceNotFoundException("Recurso nao encontrado");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void deleteClientById(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try {
            clientRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }
}
