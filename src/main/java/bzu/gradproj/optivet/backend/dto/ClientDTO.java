package bzu.gradproj.optivet.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO extends UserDTO {
    private List<PetDTO> pets; // List of pets owned by the client
}
