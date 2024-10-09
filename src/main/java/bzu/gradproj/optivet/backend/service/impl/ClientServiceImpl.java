package bzu.gradproj.optivet.backend.service.impl;

import bzu.gradproj.optivet.backend.dto.ClientDTO;
import bzu.gradproj.optivet.backend.dto.PetDTO;
import bzu.gradproj.optivet.backend.exception.ResourceNotFoundException;
import bzu.gradproj.optivet.backend.mapper.ClientMapper;
import bzu.gradproj.optivet.backend.mapper.PetMapper;
import bzu.gradproj.optivet.backend.model.entity.Client;
import bzu.gradproj.optivet.backend.model.entity.Pet;
import bzu.gradproj.optivet.backend.repository.ClientRepo;
import bzu.gradproj.optivet.backend.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepo clientRepo;

    @Override
    public ClientDTO createClient(ClientDTO clientDTO) {
        Client client = ClientMapper.toClientEntity(clientDTO);
        Client savedClient = clientRepo.save(client);
        return ClientMapper.toClientDTO(savedClient);
    }

    @Override
    public ClientDTO getClientById(Long clientId) {
        Client client = clientRepo.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with id: " + clientId));
        return ClientMapper.toClientDTO(client);
    }

    @Override
    public List<ClientDTO> getAllClients() {
        return clientRepo.findAll().stream()
                .map(ClientMapper::toClientDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ClientDTO updateClient(Long clientId, ClientDTO clientDTO) {
        Client client = clientRepo.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with id: " + clientId));

        client.setFirstName(clientDTO.getFirstName());
        client.setLastName(clientDTO.getLastName());
        client.setEmail(clientDTO.getEmail());
        client.setPhoneNumber(clientDTO.getPhoneNumber());

        Client updatedClient = clientRepo.save(client);
        return ClientMapper.toClientDTO(updatedClient);
    }

    @Override
    public void deleteClient(Long clientId) {
        clientRepo.deleteById(clientId);
    }

    @Override
    public List<PetDTO> getClientPets(Long clientId) {
        Client client = clientRepo.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with id: " + clientId));

        // Map the client's list of pets to PetDTO
        return client.getPets().stream()
                .map(PetMapper::toPetDTO)
                .collect(Collectors.toList());
    }
}
