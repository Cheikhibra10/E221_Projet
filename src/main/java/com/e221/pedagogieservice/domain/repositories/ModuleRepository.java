package com.e221.pedagogieservice.domain.repositories;

import com.cheikh.commun.core.GenericRepository;
import com.e221.pedagogieservice.domain.models.Evenement;
import com.e221.pedagogieservice.domain.models.Module;
import com.e221.pedagogieservice.domain.models.Specialite;
import com.e221.pedagogieservice.domain.models.UE;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModuleRepository extends GenericRepository<Module> {

    @Query("""
        select distinct m
        from Module m
        left join fetch m.moduleUES mu
        left join fetch mu.ue u
        where m.archive = false
    """)
    List<Module> findAllActiveWithUes();
    @Query("""
    select m
    from Module m
    left join fetch m.moduleUES mu
    left join fetch mu.ue u
    where m.id = :id
""")
    Optional<Module> findByIdWithUes(Long id);
    @Query("SELECT m FROM Module m")
    Page<Module> findAllPageableModule(Pageable pageable);

}
