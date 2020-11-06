package restaurant.core.report.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import restaurant.core.report.service.DailyReportService;

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

}
