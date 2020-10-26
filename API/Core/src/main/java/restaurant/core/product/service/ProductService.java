package restaurant.core.product.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import restaurant.core.product.repository.ProductCategoryRepository;
import restaurant.core.product.repository.ProductRepository;

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
}
