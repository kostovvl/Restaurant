package restaurant.core.report.domain;

import restaurant.core.bill.domain.BillDto;

import java.time.LocalDateTime;
import java.util.List;

public class DailyReportDto {

    private LocalDateTime date;
    private List<BillDto> bills;
    private double total;

    public DailyReportDto() {
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public List<BillDto> getBills() {
        return bills;
    }

    public void setBills(List<BillDto> bills) {
        this.bills = bills;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
