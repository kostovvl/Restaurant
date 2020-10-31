package restaurant.core.product.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import restaurant.core.product.domain.category.ProductCategory;
import restaurant.core.product.domain.category.ProductCategoryDto;
import restaurant.core.product.repository.ProductCategoryRepository;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductCategoryService {

    private final ProductCategoryRepository productCategoryRepository;
    private final ProductService productService;
    private final ModelMapper mapper;

    public ProductCategoryService(ProductCategoryRepository productCategoryRepository,
                                  ProductService productService, ModelMapper mapper) {
        this.productCategoryRepository = productCategoryRepository;
        this.productService = productService;
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

        //**Ordered alphabetically by name**//

        return this.productCategoryRepository.getAllCategories()
                .stream().map(c -> this.mapper.map(c, ProductCategoryDto.class))
                .collect(Collectors.toList());
    }



    public ProductCategoryDto findByName(String name) {
        return this.productCategoryRepository.findByName(name)
                .map(c -> this.mapper.map(c, ProductCategoryDto.class))
                .orElse(null);
    }

    public ProductCategoryDto updateCategory(long categoryId, ProductCategoryDto updatedCategory) {

        ProductCategory existing = this.productCategoryRepository.getOne(categoryId);

        existing.setName(updatedCategory.getName());

        return this.mapper.map(
                this.productCategoryRepository.saveAndFlush(existing), ProductCategoryDto.class);

    }


    public String deleteCategory(long categoryId) {

        String result = this.productCategoryRepository.findById(categoryId).orElse(null).getName();
        List<Long> productsFromTheCategoryIds = this.productService.findAllProductIdsByCategoryId(categoryId);

        for (Long productId : productsFromTheCategoryIds) {
            this.productService.deleteProduct(productId);
        }

        this.productCategoryRepository.deleteById(categoryId);

        return result;
    }

    //********** Private methods **********//

    private boolean categoryExists(String name) {
        return this.productCategoryRepository.findByName(name).orElse(null) != null;
    }


}
