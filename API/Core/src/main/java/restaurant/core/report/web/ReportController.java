package restaurant.core.report.web;

import org.hibernate.TransactionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restaurant.core.report.service.DailyReportService;

import javax.persistence.EntityExistsException;
import javax.persistence.PersistenceException;

@RestController
@RequestMapping("/report")
public class ReportController {

    private final DailyReportService dailyReportService;

    public ReportController(DailyReportService dailyReportService) {
        this.dailyReportService = dailyReportService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createDailyReport() {

        this.dailyReportService.createDailyReport();

        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @GetMapping("/get/{reportId}")
    public ResponseEntity<?> getReport(@PathVariable(name = "reportId") long reportId) {
        return new ResponseEntity<>(this.dailyReportService.getDailyReport(reportId), HttpStatus.OK);
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
