package bzu.gradproj.optivet.backend.service.impl;

import bzu.gradproj.optivet.backend.dto.*;
import bzu.gradproj.optivet.backend.mapper.CategoryMapper;
import bzu.gradproj.optivet.backend.mapper.ProductMapper;
import bzu.gradproj.optivet.backend.model.entity.*;
import bzu.gradproj.optivet.backend.repository.*;
import bzu.gradproj.optivet.backend.service.CategoryService;
import bzu.gradproj.optivet.backend.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;
    private final CategoryRepo categoryRepo;

    @Transactional
    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {

        Category category = categoryMapper.toEntity(categoryDTO);

        Category savedCategory = categoryRepo.save(category);

        return categoryMapper.toDTO(savedCategory);
    }


    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Long id) {

        Category existingCategory = categoryRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Category not found"));


        categoryRepo.save(existingCategory);
        return categoryMapper.toDTO(existingCategory);


    }




    @Override

    public List<CategoryDTO> getAllCategories() {

        List<Category> categories = categoryRepo.findAll();
        return categoryMapper.toDTOList(categories);
    }




    @Override
    public CategoryDTO getCategoryById(Long id) {

        Category existingCategory = categoryRepo.findById(id).get();
        return categoryMapper.toDTO(existingCategory);

    }

    @Override

    public void deleteCategory(Long id) {
        categoryRepo.deleteById(id);
    }


}


