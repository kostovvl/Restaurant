package restaurant.core.table.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import restaurant.core.table.domain.TableEntity;

import javax.persistence.Table;
import java.util.List;
import java.util.Optional;

@Repository
public interface TableRepository extends JpaRepository<TableEntity, Long> {

    Optional<TableEntity> findByNumber(int number);

    @Query("select t from TableEntity as t where t.waiter is null")
    List<TableEntity> getAllFreeTables();

}
