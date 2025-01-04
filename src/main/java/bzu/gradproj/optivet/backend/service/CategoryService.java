package bzu.gradproj.optivet.backend.service;

import bzu.gradproj.optivet.backend.dto.CategoryDTO;
import bzu.gradproj.optivet.backend.dto.ProductDTO;

import java.util.List;

public interface CategoryService {


    CategoryDTO createCategory(CategoryDTO categoryDTO);

    CategoryDTO updateCategory(CategoryDTO categoryDTO, Long id);

    List<CategoryDTO> getAllCategories();

    CategoryDTO getCategoryById(Long id);
    void deleteCategory(Long id);
}
