package com.e221.pedagogieservice.domain.repositories;

import com.e221.pedagogieservice.domain.models.Profil;
import com.e221.pedagogieservice.domain.models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

}
