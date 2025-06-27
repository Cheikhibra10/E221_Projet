package com.e221.pedagogieservice.domain.repositories;

import com.e221.pedagogieservice.domain.models.Devoir;
import com.e221.pedagogieservice.domain.models.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DevoirRepository extends JpaRepository<Devoir, Long> {
}
