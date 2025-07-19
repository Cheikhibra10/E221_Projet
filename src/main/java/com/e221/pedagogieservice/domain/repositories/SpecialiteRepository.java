package com.e221.pedagogieservice.domain.repositories;

import com.cheikh.commun.core.GenericRepository;
import com.e221.pedagogieservice.domain.models.Calendrier;
import com.e221.pedagogieservice.domain.models.Evenement;
import com.e221.pedagogieservice.domain.models.Specialite;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpecialiteRepository extends GenericRepository<Specialite> {

    @Query("""
        select distinct s
        from Specialite s
        left join fetch s.niveauxSpecialites ns
        left join fetch ns.niveau n
        where s.archive = false
    """)
    List<Specialite> findAllActiveWithNiveaux();

    @Query("""
    select s
    from Specialite s
    left join fetch s.niveauxSpecialites ns
    left join fetch ns.niveau n
    where s.id = :id
""")
    Optional<Specialite> findByIdWithNiveaux(Long id);
    @Query("SELECT s FROM Specialite s")
    Page<Specialite> findAllPageableSpecialite(Pageable pageable);

}
