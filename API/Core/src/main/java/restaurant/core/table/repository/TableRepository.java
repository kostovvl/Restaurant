package restaurant.core.table.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import restaurant.core.table.domain.TableEntity;

@Repository
public interface TableRepository extends JpaRepository<TableEntity, Long> {
}
