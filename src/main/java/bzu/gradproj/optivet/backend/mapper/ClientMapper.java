package bzu.gradproj.optivet.backend.mapper;

import bzu.gradproj.optivet.backend.dto.ClientDTO;
import bzu.gradproj.optivet.backend.model.entity.Client;
import bzu.gradproj.optivet.backend.mapper.PetMapper; // Ensure to import the PetMapper class

import java.util.List;
import java.util.stream.Collectors;

public class ClientMapper {

    // Method to convert Client to ClientDTO
    public static ClientDTO toClientDTO(Client client) {
        if (client == null) {
            return null;
        }

        ClientDTO clientDTO = new ClientDTO();
        // Map fields from Client to ClientDTO
        clientDTO.setUserId(client.getId());
        clientDTO.setEmail(client.getEmail());

        clientDTO.setRole(client.getRole());

        clientDTO.setPhoneNumber(client.getPhoneNumber());
        clientDTO.setDateOfBirth(client.getDateOfBirth());

        clientDTO.setFirstName(client.getFirstName());
        clientDTO.setLastName(client.getLastName());

        clientDTO.setCreatedAt(client.getCreatedAt());
        clientDTO.setUpdatedAt(client.getUpdatedAt());


        clientDTO.setPets(PetMapper.petListToPetDTOList(client.getPets())); // Map pets using PetMapper

        return clientDTO;
    }

    public static Client toClientEntity(ClientDTO clientDTO) {
        if (clientDTO == null) {
            return null;
        }

        Client client = new Client();
        // Map fields from ClientDTO to Client
        client.setId(clientDTO.getUserId()); // Assuming setId() sets userId
        client.setEmail(clientDTO.getEmail());

        client.setFirstName(clientDTO.getFirstName());
        client.setLastName(clientDTO.getLastName());

        client.setRole(clientDTO.getRole());
        client.setPhoneNumber(clientDTO.getPhoneNumber());
        client.setDateOfBirth(clientDTO.getDateOfBirth());

        client.setCreatedAt(clientDTO.getCreatedAt());
        client.setUpdatedAt(clientDTO.getUpdatedAt());

        client.setPets(PetMapper.petDTOListToPetList(clientDTO.getPets())); // Map pets using PetMapper

        return client;
    }
}
