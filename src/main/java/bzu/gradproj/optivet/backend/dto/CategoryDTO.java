package bzu.gradproj.optivet.backend.dto;

//import bzu.gradproj.optivet.backend.model.entity.ProductCategory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {

    private Long id;

    @NotNull(message = "Category name cannot be null")
    private String name;



    private String categoryImageUrl;



}