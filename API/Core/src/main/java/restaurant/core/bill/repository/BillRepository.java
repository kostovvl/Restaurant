package restaurant.core.bill.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import restaurant.core.bill.domain.Bill;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
}
