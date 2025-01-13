package bzu.gradproj.optivet.backend.mapper;

import bzu.gradproj.optivet.backend.dto.AllergiesDTO;

import bzu.gradproj.optivet.backend.dto.ClientDTO;
import bzu.gradproj.optivet.backend.dto.PetForAdoptionDTO;
import bzu.gradproj.optivet.backend.dto.ProductDTO;
import bzu.gradproj.optivet.backend.model.entity.Allergies;
import bzu.gradproj.optivet.backend.model.entity.Client;
import bzu.gradproj.optivet.backend.model.entity.PetForAdoption;
import bzu.gradproj.optivet.backend.model.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PetForAdoptionMapper {

    //    @Mapping(source = "productCategory", target = "productCategory")
    //@Mapping(source = "productCategory", target = "productCategory")
    @Mapping(source = "id", target = "id")
    PetForAdoptionDTO toDTO(PetForAdoption petForAdoption);


    //    @Mapping(source = "productCategory", target = "productCategory")
    //@Mapping(source = "productCategory", target = "productCategory")
    @Mapping(source = "id", target = "id")


    PetForAdoption toEntity(PetForAdoptionDTO petForAdoptionDTO);


    List<PetForAdoptionDTO> toDTOList(List<PetForAdoption> petsForAdoption);
}