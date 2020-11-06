package restaurant.core.report.service;

import org.springframework.stereotype.Service;
import restaurant.core.bill.domain.Bill;
import restaurant.core.bill.repository.BillRepository;
import restaurant.core.report.domain.DailyReport;
import restaurant.core.report.repository.ReportRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class DailyReportService {

    private final ReportRepository reportRepository;
    private final BillRepository billRepository;

    public DailyReportService(ReportRepository reportRepository, BillRepository billRepository) {
        this.reportRepository = reportRepository;
        this.billRepository = billRepository;
    }

    public void createDailyReport() {
        DailyReport dailyReport = new DailyReport();
        dailyReport.setDate(LocalDateTime.now());
        double totalTurnover = 0;
        List<Long> allDailyBillsIds = new ArrayList<>();
        List<Bill> allDailyBills = this.billRepository.findAll();

       List<Bill> bills = this.billRepository.findAll();

        for (Bill bill : bills) {
            totalTurnover += bill.getTotalPrice();
            allDailyBillsIds.add(bill.getId());
        }

        dailyReport.setTotal(totalTurnover);
        dailyReport.setBills(allDailyBillsIds);

        this.reportRepository.saveAndFlush(dailyReport);
    }
}
