package restaurant.core.product.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restaurant.core.product.domain.category.ProductCategoryDto;
import restaurant.core.product.service.ProductCategoryService;

import javax.persistence.EntityExistsException;
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
        try {
          ProductCategoryDto result =  this.productCategoryService.addCategory(newCategory);
          return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (EntityExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (Exception e1) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductCategoryDto>> getAllCategories() {
        return new ResponseEntity<>(this.productCategoryService.getAll(), HttpStatus.OK);
    }

}
