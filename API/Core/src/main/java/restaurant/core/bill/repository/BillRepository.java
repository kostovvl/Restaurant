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

    @Query("select b from Bill as b where b.table.id =:tableId order by b.id")
    List<Bill> findByTableId(long tableId);

    @Query("select b from Bill as b where b.table is null order by b.id")
    List<Bill> findByForTakeAway();

    @Query("select b from Bill as b where b.table.id =:tableId and b.waiter.id =:waiterId order by b.id")
    List<Bill> findByTableAndWaiterId(long tableId, long waiterId);

    @Query("select b from Bill as b where b.table is null and b.waiter.id =:waiterId order by b.id")
    List<Bill> findByForTakeAwayAndWaiterId(long waiterId);

}
