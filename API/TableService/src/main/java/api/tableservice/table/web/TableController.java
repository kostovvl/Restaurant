package api.tableservice.table.web;

import api.tableservice.table.domain.TableContainer;
import api.tableservice.table.domain.TableEntityDto;
import api.tableservice.table.service.TableService;
import org.hibernate.TransactionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import javax.persistence.PersistenceException;
import java.util.List;

@RestController
public class TableController {

    private final TableService tableService;

    public TableController(TableService tableService) {
        this.tableService = tableService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllTables() {
        TableContainer result =  new TableContainer(this.tableService.getAllTables());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/free")
    public ResponseEntity<?> getAllFreeTables() {
        TableContainer result =  new TableContainer(this.tableService.getAllFreeTables());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //todo make the method reachable only for admin
    @PostMapping("/create/{tableNumber}")
    public ResponseEntity<?> createNewTable(@PathVariable(name = "tableNumber") int tableNumber) {
        this.tableService.createTable(tableNumber);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/add_waiter/{waiterId}/{tableId}")
    public ResponseEntity<String> addTableToWaiter(@PathVariable(name = "waiterId") long waiterId,
                                                   @PathVariable(name = "tableId") long tableId) {
        this.tableService.addWaiter(waiterId, tableId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/remove_waiter/{tableId}")
    public ResponseEntity<String> removeTableFromWaiter(@PathVariable(name = "tableId") long tableId) {

        this.tableService.removeWaiter(tableId);

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
