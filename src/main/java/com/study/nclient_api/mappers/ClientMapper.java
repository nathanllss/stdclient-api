package com.study.nclient_api.mappers;

import com.study.nclient_api.dto.ClientDTO;
import com.study.nclient_api.entities.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientDTO clientToClientDTO(Client client);

    Client clientDTOToClient(ClientDTO clientDTO);
}
