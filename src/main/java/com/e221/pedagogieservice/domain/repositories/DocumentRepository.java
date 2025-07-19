package com.e221.pedagogieservice.domain.repositories;

import com.cheikh.commun.core.GenericRepository;
import com.e221.pedagogieservice.domain.models.Document;
import com.e221.pedagogieservice.domain.models.Specialite;
import com.e221.pedagogieservice.domain.models.UE;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DocumentRepository extends GenericRepository<Document> {
    @Query("""
        select distinct d
        from Document d
        left join fetch d.documentParNiveaux dn
        left join fetch dn.niveau n
        where d.archive = false
    """)
    List<Document> findAllActiveWithNiveaux();
    @Query("""
    select d
    from Document d
    left join fetch d.documentParNiveaux dn
    left join fetch dn.niveau n
    where d.id = :id
""")
    Optional<Document> findByIdWithNiveaux(Long id);
    @Query("SELECT d FROM Document d")
    Page<Document> findAllPageableDocument(Pageable pageable);

}
