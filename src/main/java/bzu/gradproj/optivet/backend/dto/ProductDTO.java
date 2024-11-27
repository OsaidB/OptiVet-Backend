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
public class ProductDTO {

    private Long id;

    @NotNull(message = "Product name cannot be null")
    private String name;


    @NotNull(message = "Product url cannot be null")
    private String productImageUrl;

    private int quantity;

    private double price;


    @NotNull(message = "Product category cannot be null")
    private String productCategory;


    //   @NotNull(message = "Medical_history ID cannot be null")


}