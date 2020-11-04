package restaurant.core.table.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import restaurant.core.table.domain.TableEntity;

import java.util.Optional;

@Repository
public interface TableRepository extends JpaRepository<TableEntity, Long> {

    Optional<TableEntity> findByNumber(int number);

}
