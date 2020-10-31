package restaurant.core.product.web;

import org.hibernate.TransactionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restaurant.core.configuration.Global;
import restaurant.core.product.domain.product.ProductDto;
import restaurant.core.product.service.ProductService;

import javax.persistence.EntityExistsException;
import javax.persistence.PersistenceException;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNewProduct(@RequestBody()ProductDto productDto) {
        ProductDto result = this.productService.addNew(productDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> result = this.productService.getAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/by_category/{categoryId}")
    public ResponseEntity<List<ProductDto>> getAllProductsByCategory(@PathVariable(name = "categoryId") long categoryId) {
        List<ProductDto> result = this.productService.getAllByCategory(categoryId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/details/{productId}")
    public ResponseEntity<ProductDto> productDetails(@PathVariable(name = "productId") long productId) {
        ProductDto result = this.productService.getDetails(productId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

   //todo preauthorize only for admin
    @PutMapping("/update/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable(name = "productId") long productId,
                                                    @RequestBody() ProductDto updatedProduct) {
        ProductDto result = this.productService.updateProduct(productId, updatedProduct);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //todo preauthorize for admin
    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable(name = "productId") long productId) {
        String productName = this.productService.deleteProduct(productId);

        return new ResponseEntity<>(String.format(Global.Product_Deleted_Message, productName), HttpStatus.OK);
    }

    //**********Exception Handlers*************//

    @ExceptionHandler({PersistenceException.class, TransactionException.class})
    public ResponseEntity<String> handleDbExceptions(EntityExistsException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAllExceptions(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
