package com.e221.pedagogieservice.domain.repositories;

import com.cheikh.commun.core.GenericRepository;
import com.e221.pedagogieservice.domain.models.TypeUe;
import com.e221.pedagogieservice.domain.models.UE;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeUeRepository extends GenericRepository<TypeUe> {
    @Query("SELECT t FROM TypeUe t")
    Page<TypeUe> findAllPageableTypeUe(Pageable pageable);

}
