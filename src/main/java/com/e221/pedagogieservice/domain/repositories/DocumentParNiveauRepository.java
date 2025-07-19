package com.e221.pedagogieservice.domain.repositories;

import com.e221.pedagogieservice.domain.models.Document;
import com.e221.pedagogieservice.domain.models.DocumentParNiveau;
import com.e221.pedagogieservice.domain.models.Niveau;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DocumentParNiveauRepository extends JpaRepository<DocumentParNiveau, Long> {
    Optional<DocumentParNiveau> findByNiveauAndDocumentAndArchiveFalse(Niveau niveau, Document document);

}
