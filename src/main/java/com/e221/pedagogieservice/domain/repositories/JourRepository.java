package com.e221.pedagogieservice.domain.repositories;

import com.e221.pedagogieservice.domain.models.Jour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface JourRepository extends JpaRepository<Jour, Long> {
}
