package com.e221.pedagogieservice.domain.repositories;

import com.cheikh.commun.core.GenericRepository;
import com.e221.pedagogieservice.domain.models.Civility;
import com.e221.pedagogieservice.domain.models.Classe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CivilityRepository extends GenericRepository<Civility> {

    @Query("SELECT c FROM Civility c")
    Page<Civility> findAllPageableCivility(Pageable pageable);
}

