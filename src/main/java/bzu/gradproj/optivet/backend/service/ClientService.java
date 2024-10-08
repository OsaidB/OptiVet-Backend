package bzu.gradproj.optivet.backend.service;

import bzu.gradproj.optivet.backend.dto.ClientDTO;
import bzu.gradproj.optivet.backend.dto.PetDTO;
import java.util.List;

public interface ClientService {
    ClientDTO createClient(ClientDTO clientDTO);
    ClientDTO getClientById(Long clientId);
    List<ClientDTO> getAllClients();
    ClientDTO updateClient(Long clientId, ClientDTO clientDTO);
    void deleteClient(Long clientId);

    // New method to retrieve a client's pets
    List<PetDTO> getClientPets(Long clientId);
}
