package bzu.gradproj.optivet.backend.service.impl;

import bzu.gradproj.optivet.backend.dto.*;
import bzu.gradproj.optivet.backend.mapper.ProductMapper;
import bzu.gradproj.optivet.backend.model.entity.*;
import bzu.gradproj.optivet.backend.repository.*;
import bzu.gradproj.optivet.backend.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;
    private final ProductRepo productRepo;

    @Transactional
    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {

        Product product = productMapper.toEntity(productDTO);
        //product.setId(productDTO.getId());
        product.setName(productDTO.getName());

      //  product.setProductCategory(ProductCategory.);
        //product.setQuantity(productDTO.getQuantity());
        Product savedProduct = productRepo.save(product);
        //savedProduct.setProductCategory(productDTO.getProductCategory());
        return productMapper.toDTO(savedProduct);
    }


//    @Override
//    public AllergiesDTO createAllergy(AllergiesDTO allergyDTO, Long petId) {
//
//
//
//        allergyDTO.setMedicalHistoryId(petRepo.findById(petId).get().getMedicalHistoryy().getId());
//
//        Allergies allergy = allergiesMapper.toEntity(allergyDTO);
//
//        MedicalHistory medicalHistory = medRepo.findById(allergyDTO.getMedicalHistoryId())
//                .orElseThrow(() -> new RuntimeException("Medical history not found"));
//        allergy.setMedicalHistory(medicalHistory);
//
//        Allergies savedAllergy = allergiesRepo.save(allergy);
//        return allergiesMapper.toDTO(savedAllergy);
//
//    }


//    private Long id;
//
//    @NotNull(message = "Product name cannot be null")
//    private String name;
//
//    @NotNull(message = "Prosuct category cannot be null")
//    private String productCategory;
//
//
//    private int quantity;
//
//
//    //   @NotNull(message = "Medical_history ID cannot be null")
//    private Long orderId;

    @Override
    public ProductDTO updateProduct(ProductDTO productDTO, Long productId) {

        Product existingProduct = productRepo.findById(productId).orElseThrow(() -> new EntityNotFoundException("Product not found"));


        productRepo.save(existingProduct);
        return productMapper.toDTO(existingProduct);


    }


    @Override

    public List<ProductDTO> getAllProducts() {

        List<Product> products = productRepo.findAll();
        return productMapper.toDTOList(products);
    }


    @Override

    public void deleteProduct(Long productId) {
        productRepo.deleteById(productId);
    }


}
