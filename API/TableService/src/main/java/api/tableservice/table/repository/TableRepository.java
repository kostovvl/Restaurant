package api.tableservice.table.repository;

import api.tableservice.table.domain.TableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface TableRepository extends JpaRepository<TableEntity, Long> {

    Optional<TableEntity> findByNumber(int number);

    @Query("select t from TableEntity as t where t.waiterId = 0")
    List<TableEntity> getAllFreeTables();

}
