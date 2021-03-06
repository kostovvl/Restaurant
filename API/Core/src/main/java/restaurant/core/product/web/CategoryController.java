package restaurant.core.product.web;

import org.hibernate.TransactionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restaurant.core.configuration.Global;
import restaurant.core.product.domain.category.ProductCategoryDto;
import restaurant.core.product.service.ProductCategoryService;

import javax.persistence.EntityExistsException;
import javax.persistence.PersistenceException;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final ProductCategoryService productCategoryService;

    public CategoryController(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCategory(@RequestBody ProductCategoryDto newCategory){

          ProductCategoryDto result =  this.productCategoryService.addCategory(newCategory);
          return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductCategoryDto>> getAllCategories() {
        return new ResponseEntity<>(this.productCategoryService.getAll(), HttpStatus.OK);
    }
    //todo preauthorize for admin
    @PutMapping("/update/{categoryId}")
    public ResponseEntity<ProductCategoryDto> updateCategory(@PathVariable(name = "categoryId") long categoryId,
                                                             @RequestBody ProductCategoryDto updatedCategory) {
        ProductCategoryDto result = this.productCategoryService.updateCategory(categoryId, updatedCategory);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200/")
    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable(name = "categoryId") long categoryId) {
        String categoryName = this.productCategoryService.deleteCategory(categoryId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ExceptionHandler({PersistenceException.class, TransactionException.class})
    public ResponseEntity<String> handleDbExceptions(EntityExistsException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAllExceptions(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }



}
