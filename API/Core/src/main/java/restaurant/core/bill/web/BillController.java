package restaurant.core.bill.web;

import org.hibernate.TransactionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restaurant.core.bill.service.BillService;
import restaurant.core.configuration.Global;

import javax.persistence.EntityExistsException;
import javax.persistence.PersistenceException;

@RestController
@RequestMapping("/bills")
public class BillController {

    private final BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }

    @PostMapping("/create/{waiterId}/{tableId}")
    public ResponseEntity<String> createNewBill(@PathVariable(name = "waiterId") long waiterId,
                                        @PathVariable(name = "tableId") long tableId) {
        long billId = this.billService.createNewBill(waiterId, tableId);
        return new ResponseEntity<>(String.format(Global.Bill_Created_Message, billId), HttpStatus.OK);
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
