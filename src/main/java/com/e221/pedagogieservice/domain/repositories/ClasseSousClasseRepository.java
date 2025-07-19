package com.e221.pedagogieservice.domain.repositories;

import com.cheikh.commun.core.GenericRepository;
import com.e221.pedagogieservice.domain.models.Classe;
import com.e221.pedagogieservice.domain.models.ClasseSousClasse;
import com.e221.pedagogieservice.domain.models.SousClasse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClasseSousClasseRepository extends GenericRepository<ClasseSousClasse> {

    @Query("SELECT c FROM ClasseSousClasse c")
    Page<ClasseSousClasse> findAllPageableClasseSousClasse(Pageable pageable);
}
