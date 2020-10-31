package restaurant.core.product.service;

import jdk.jfr.Category;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import restaurant.core.product.domain.category.ProductCategory;
import restaurant.core.product.domain.category.ProductCategoryDto;
import restaurant.core.product.repository.ProductCategoryRepository;

import javax.persistence.EntityExistsException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductCategoryService {

    private final ProductCategoryRepository productCategoryRepository;
    private final ModelMapper mapper;

    public ProductCategoryService(ProductCategoryRepository productCategoryRepository, ModelMapper mapper) {
        this.productCategoryRepository = productCategoryRepository;
        this.mapper = mapper;
    }



    public ProductCategoryDto addCategory(ProductCategoryDto newCategory){
        if (this.categoryExists(newCategory.getName())) {
            throw new EntityExistsException("Such Category Already Exists in DB!");
        }

        ProductCategory forSeed = this.mapper.map(newCategory, ProductCategory.class);

        return this.mapper.map(this.productCategoryRepository.saveAndFlush(forSeed), ProductCategoryDto.class);
    }

    public List<ProductCategoryDto> getAll() {

        return this.productCategoryRepository.getAllCategories()
                .stream().map(c -> this.mapper.map(c, ProductCategoryDto.class))
                .collect(Collectors.toList());
    }

    public ProductCategoryDto findByName(String name) {
        return this.productCategoryRepository.findByName(name)
                .map(c -> this.mapper.map(c, ProductCategoryDto.class))
                .orElse(null);
    }

    private boolean categoryExists(String name) {
        return this.productCategoryRepository.findByName(name).orElse(null) != null;
    }
}
