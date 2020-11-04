package restaurant.core.user.web;

import org.hibernate.TransactionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restaurant.core.user.domain.userEntity.UserEntityDto;
import restaurant.core.user.service.UserEntityService;

import javax.persistence.EntityExistsException;
import javax.persistence.PersistenceException;

@RestController
@RequestMapping("/waiters")
public class UserController {

    private final UserEntityService userEntityService;

    public UserController(UserEntityService userEntityService) {
        this.userEntityService = userEntityService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> createNewUser(@RequestBody UserEntityDto newUser) {
        UserEntityDto result = this.userEntityService.createNewUser(newUser);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //********** Error Handlers **********//

    @ExceptionHandler({PersistenceException.class, TransactionException.class})
    public ResponseEntity<String> handleDbExceptions(EntityExistsException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAllExceptions(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
