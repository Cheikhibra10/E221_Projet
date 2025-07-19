package com.e221.pedagogieservice.domain.repositories;

import com.cheikh.commun.core.GenericRepository;
import com.e221.pedagogieservice.domain.models.Evenement;
import com.e221.pedagogieservice.domain.models.Specialite;
import com.e221.pedagogieservice.domain.models.UE;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UeRepository extends GenericRepository<UE> {

    @Query("""
        select distinct u
        from UE u
        left join fetch u.mentionUES mu
        left join fetch mu.mention m
        where u.archive = false
    """)
    List<UE> findAllActiveWithMentions();
    @Query("""
    select u
    from UE u
    left join fetch u.mentionUES mu
    left join fetch mu.mention m
    where u.id = :id
""")
    Optional<UE> findByIdWithMentions(Long id);
    @Query("SELECT u FROM UE u")
    Page<UE> findAllPageableUE(Pageable pageable);

}
