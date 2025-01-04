package bzu.gradproj.optivet.backend.mapper;

import bzu.gradproj.optivet.backend.dto.AllergiesDTO;

import bzu.gradproj.optivet.backend.dto.CategoryDTO;
import bzu.gradproj.optivet.backend.dto.ClientDTO;
import bzu.gradproj.optivet.backend.dto.ProductDTO;
import bzu.gradproj.optivet.backend.model.entity.Allergies;
import bzu.gradproj.optivet.backend.model.entity.Category;
import bzu.gradproj.optivet.backend.model.entity.Client;
import bzu.gradproj.optivet.backend.model.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {



    @Mapping(source = "id", target = "id")
    CategoryDTO toDTO(Category category);



    @Mapping(source = "id", target = "id")
    Category toEntity(CategoryDTO categoryDTO);


    List<CategoryDTO> toDTOList(List<Category> categories);
}