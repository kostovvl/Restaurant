package restaurant.core.bill.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import restaurant.core.bill.domain.Bill;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {

    @Query("select b from Bill as b where b.waiter.id =:waiterId order by b.id")
    List<Bill> findByWaiterId(long waiterId);

}
