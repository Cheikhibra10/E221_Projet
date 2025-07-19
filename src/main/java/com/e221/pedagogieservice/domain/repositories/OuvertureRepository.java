package com.e221.pedagogieservice.domain.repositories;

import com.cheikh.commun.core.GenericRepository;
import com.e221.pedagogieservice.domain.models.Evenement;
import com.e221.pedagogieservice.domain.models.Ouverture;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface OuvertureRepository extends GenericRepository<Ouverture> {
    @Modifying
    @Transactional
    @Query("update Ouverture o set o.statut = 'Cloturer' " +
            "where o.anneeScolaire.id = :id and o.statut <> 'Cloturer'")
    int closeAllByAnnee(@Param("id") Long id);
    @Modifying
    @Transactional
    @Query("update Ouverture o set o.statut = 'En_Cours' " +
            "where o.anneeScolaire.id = :id and o.statut <> 'En_Cours'")
    int openAllByAnnee(@Param("id") Long id);

    List<Ouverture> findByAnneeScolaireId(Long anneeId);

    @Query("SELECT o FROM Ouverture o")
    Page<Ouverture> findAllPageableOuverture(Pageable pageable);

}
