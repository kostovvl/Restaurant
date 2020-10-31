package restaurant.core.product.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import restaurant.core.product.domain.category.ProductCategory;
import restaurant.core.product.domain.product.Product;
import restaurant.core.product.domain.product.ProductDto;
import restaurant.core.product.repository.ProductCategoryRepository;
import restaurant.core.product.repository.ProductRepository;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.stream.Collectors;

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



    public List<ProductDto> getAll() {

        //**Ordered alphabetically by name**//
        return this.productRepository.findAllProducts()
                .stream()
                .map(p -> {
                    ProductDto productDto = this.mapper.map(p, ProductDto.class);
                    productDto.setCategoryId(p.getCategory().getId());
                    productDto.setCategoryName(p.getCategory().getName());
                    return productDto;
                })
                .collect(Collectors.toList());

    }

    public List<ProductDto> getAllByCategory(long categoryId) {

        //**Ordered alphabetically by name**//
        return this.productRepository.findAllProductsByCategory(categoryId)
                .stream()
                .map(p -> {
                    ProductDto productDto = this.mapper.map(p, ProductDto.class);
                    productDto.setCategoryId(p.getCategory().getId());
                    productDto.setCategoryName(p.getCategory().getName());
                    return productDto;
                })
                .collect(Collectors.toList());

    }


    public ProductDto getDetails(long productId) {

       return this.productRepository.findById(productId)
               .map(p -> {
                   ProductDto result = this.mapper.map(p, ProductDto.class);
                   result.setCategoryId(p.getCategory().getId());
                   result.setCategoryName(p.getCategory().getName());
                   return result;
               }).orElse(null);

    }

    public ProductDto updateProduct(long productId, ProductDto updatedProduct) {
        Product existing = this.productRepository.getOne(productId);
        existing.setName(updatedProduct.getName());
        existing.setPrice(updatedProduct.getPrice());
        existing.setCategory(this.productCategoryRepository.getOne(updatedProduct.getCategoryId()));

        return this.mapper.map(
                this.productRepository.saveAndFlush(existing), ProductDto.class
        );
    }




    //**********Private Methods**********//

    private boolean productExists(String name) {
        return this.productRepository.findByName(name).orElse(null) != null;
    }



}
