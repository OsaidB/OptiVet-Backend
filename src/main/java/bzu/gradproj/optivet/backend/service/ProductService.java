package bzu.gradproj.optivet.backend.service;

import bzu.gradproj.optivet.backend.dto.ProductDTO;

import java.util.List;

public interface ProductService {


    ProductDTO createProduct(ProductDTO productDTO);

    ProductDTO updateProduct(ProductDTO productDTO, Long productId);

    List<ProductDTO> getAllProducts();


    void deleteProduct(Long productId);
}