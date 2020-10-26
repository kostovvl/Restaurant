package restaurant.core.report.service;

import org.springframework.stereotype.Service;
import restaurant.core.report.repository.ReportRepository;

@Service
public class DailyReportService {

    private final ReportRepository reportRepository;

    public DailyReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }
}
