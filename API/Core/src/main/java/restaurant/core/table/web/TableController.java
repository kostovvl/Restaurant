package restaurant.core.table.web;

import org.hibernate.TransactionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restaurant.core.configuration.Global;
import restaurant.core.table.domain.TableEntityDto;
import restaurant.core.table.service.TableService;

import javax.persistence.EntityExistsException;
import javax.persistence.PersistenceException;
import java.util.List;

@RestController
@RequestMapping("/tables")
public class TableController {

    private final TableService tableService;

    public TableController(TableService tableService) {
        this.tableService = tableService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllTables() {
        List<TableEntityDto> result = this.tableService.getAllTables();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/free")
    public ResponseEntity<?> getAllFreeTables() {
        List<TableEntityDto> result = this.tableService.getAllFreeTables();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //todo make the method reachable only for admin
    @PostMapping("/create/{tableNumber}")
    public ResponseEntity<String> createNewTable(@PathVariable(name = "tableNumber") int tableNumber) {
        this.tableService.createTable(tableNumber);
        return new ResponseEntity<>(HttpStatus.OK);
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
