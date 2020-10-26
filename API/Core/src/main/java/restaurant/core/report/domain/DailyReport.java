package restaurant.core.report.domain;

import restaurant.core.configuration.BaseEntity;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "reports")
public class DailyReport extends BaseEntity {

    private String day;
    private List<Long> bills;
    private double total;

    public DailyReport() {
    }

    @Column(name = "day_of_week")
    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    @ElementCollection()
    public List<Long> getBills() {
        return bills;
    }

    public void setBills(List<Long> bills) {
        this.bills = bills;
    }

    @Column(name = "total")
    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
