package restaurant.core.bill.web;

import org.hibernate.TransactionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restaurant.core.bill.domain.BillDto;
import restaurant.core.bill.service.BillService;
import javax.persistence.EntityExistsException;
import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bills")
public class BillController {

    private final BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }

    @PostMapping("/create/{waiterId}/{tableId}")
    public ResponseEntity<?> createNewBill(@PathVariable(name = "waiterId") long waiterId,
                                        @PathVariable(name = "tableId") long tableId) {
        BillDto result = this.billService.createNewBill(waiterId, tableId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //*** Get Bill Methods ***//

    @GetMapping("/get/all")
    public ResponseEntity<List<BillDto>> getAllBills() {
        return new ResponseEntity<>(this.billService.getAllBills(), HttpStatus.OK);
    }

    @GetMapping("/get/by_waiter/{waiterId}")
    public ResponseEntity<List<BillDto>> getBillsByWaiter(@PathVariable(name = "waiterId") long waiterId) {
        return new ResponseEntity<>(this.billService.getBillsByWaiter(waiterId), HttpStatus.OK);
    }

    @GetMapping("/get/by_table/{tableId}")
    public ResponseEntity<List<BillDto>> getBillsByTable(@PathVariable(name = "tableId") long tableId) {
        return new ResponseEntity<>(this.billService.getBillsByTable(tableId), HttpStatus.OK);
    }

    @GetMapping("/get/by_table_and_waiter/{tableId}/{waiterId}")
    public ResponseEntity<List<BillDto>> getBillsByTableAndWaiter(@PathVariable(name = "tableId") long tableId,
                                                                  @PathVariable(name = "waiterId") long waiterId){
        return new ResponseEntity<>(this.billService.getBillsByTableAndWaiter(tableId, waiterId), HttpStatus.OK);
    }

    @GetMapping("/get/details/{billId}")
    public ResponseEntity<BillDto> getDetails(@PathVariable(name = "billId") long billId) {
        BillDto result = this.billService.findById(billId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //*** End of Get Bill Methods ***//

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/add/product/{billId}/{productId}/{quantity}")
    public ResponseEntity<?> addProduct(@PathVariable(name = "billId") long billId,
                                        @PathVariable(name = "productId") long productId,
                                        @PathVariable(name = "quantity") int quantity) {

        this.billService.addProduct(billId, productId, quantity);

        return new ResponseEntity<>( HttpStatus.OK);
    }

    @DeleteMapping("/delete/product/{billId}/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable(name = "billId") long billId,
                                        @PathVariable(name = "productId") long productId) {

        this.billService.deleteProduct(billId, productId);

        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @PutMapping("/close/{billId}")
    public ResponseEntity<?> closeBill(@PathVariable(name = "billId") long billId) {
        this.billService.closeBill(billId);

        return new ResponseEntity<>("Success", HttpStatus.OK);
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
