package bzu.gradproj.optivet.backend.mapper;

import bzu.gradproj.optivet.backend.dto.AllergiesDTO;

import bzu.gradproj.optivet.backend.dto.ClientDTO;
import bzu.gradproj.optivet.backend.dto.ProductDTO;
import bzu.gradproj.optivet.backend.model.entity.Allergies;
import bzu.gradproj.optivet.backend.model.entity.Client;
import bzu.gradproj.optivet.backend.model.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(source = "productCategory", target = "productCategory")
    ProductDTO toDTO(Product product);


    @Mapping(source = "productCategory", target = "productCategory")
    Product toEntity(ProductDTO productDTO);


    List<ProductDTO> toDTOList(List<Product> products);
}