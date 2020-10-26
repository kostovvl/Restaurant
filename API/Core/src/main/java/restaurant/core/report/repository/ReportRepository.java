package restaurant.core.report.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import restaurant.core.report.domain.DailyReport;

@Repository
public interface ReportRepository extends JpaRepository<DailyReport, Long> {
}
