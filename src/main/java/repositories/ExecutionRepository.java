package repositories;

import models.Execution;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface ExecutionRepository  extends PagingAndSortingRepository<Execution,Long> {
    List<Execution> findAll(Pageable pageable);
    @Query("SELECT e FROM models.Execution e JOIN FETCH e.executionItems WHERE e.identifier = (:id)")
    Optional<Execution> findById(Long id);
}
