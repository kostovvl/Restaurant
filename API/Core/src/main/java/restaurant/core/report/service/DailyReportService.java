package restaurant.core.report.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import restaurant.core.bill.domain.Bill;
import restaurant.core.bill.domain.BillDto;
import restaurant.core.bill.repository.BillRepository;
import restaurant.core.bill.service.BillService;
import restaurant.core.report.domain.DailyReport;
import restaurant.core.report.domain.DailyReportDto;
import restaurant.core.report.repository.ReportRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class DailyReportService {

    private final ReportRepository reportRepository;
    private final BillRepository billRepository;
    private final BillService billService;
    private final ModelMapper mapper;

    public DailyReportService(ReportRepository reportRepository, BillRepository billRepository,
                              BillService billService, ModelMapper mapper) {
        this.reportRepository = reportRepository;
        this.billRepository = billRepository;
        this.billService = billService;

        this.mapper = mapper;
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

    public DailyReportDto getDailyReport(long reportId) { //todo has to be with date

        return this.reportRepository.findById(reportId)
        .map(r -> {
            DailyReportDto reportDto = this.mapper.map(r, DailyReportDto.class);
            List<BillDto> billDtos = new ArrayList<>();
            for (Long bill : r.getBills()) {
                BillDto billDto = this.billService.findById(bill);
                billDtos.add(billDto);
            }
            reportDto.setBills(billDtos);
            return reportDto;
        }).orElse(null);
    }
}
