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

    @ExceptionHandler({PersistenceException.class, TransactionException.class})
    public ResponseEntity<String> handleDbExceptions(EntityExistsException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAllExceptions(Exception e) {
        return new ResponseEntity<>(Global.Try_Again_Message, HttpStatus.BAD_REQUEST);
    }

}
