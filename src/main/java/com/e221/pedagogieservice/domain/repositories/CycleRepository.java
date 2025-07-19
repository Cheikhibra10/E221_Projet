package com.e221.pedagogieservice.domain.repositories;

import com.cheikh.commun.core.GenericRepository;
import com.e221.pedagogieservice.domain.models.Cycle;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CycleRepository extends GenericRepository<Cycle> {

    @Query("SELECT c FROM Cycle c")
    Page<Cycle> findAllPageableCycle(Pageable pageable);
}
