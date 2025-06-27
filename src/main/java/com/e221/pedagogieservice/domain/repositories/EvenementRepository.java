package com.e221.pedagogieservice.domain.repositories;

import com.cheikh.commun.core.GenericRepository;
import com.e221.pedagogieservice.domain.models.Evenement;
import org.springframework.stereotype.Repository;

@Repository
public interface EvenementRepository extends GenericRepository<Evenement> {
}
