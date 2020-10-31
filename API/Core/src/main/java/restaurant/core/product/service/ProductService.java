package restaurant.core.product.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import restaurant.core.product.domain.category.ProductCategory;
import restaurant.core.product.domain.product.Product;
import restaurant.core.product.domain.product.ProductDto;
import restaurant.core.product.repository.ProductCategoryRepository;
import restaurant.core.product.repository.ProductRepository;

import javax.persistence.EntityExistsException;

@Service
public class ProductService {

    private final ProductCategoryRepository productCategoryRepository;
    private final ProductRepository productRepository;
    private final ModelMapper mapper;

    public ProductService(ProductCategoryRepository productCategoryRepository,
                          ProductRepository productRepository, ModelMapper mapper) {
        this.productCategoryRepository = productCategoryRepository;
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    public ProductDto addNew(ProductDto newProduct) {
        if (this.productExists(newProduct.getName())) {
            throw new EntityExistsException("Such Product Already Exists!");
        }
        ProductCategory category = this.productCategoryRepository.getOne(newProduct.getCategoryId());

        Product product = this.mapper.map(newProduct, Product.class);
        product.setCategory(category);

        this.productRepository.saveAndFlush(product);
        newProduct.setId(product.getId());

        return newProduct;
    }

    private boolean productExists(String name) {
        return this.productRepository.findByName(name).orElse(null) != null;
    }

}
