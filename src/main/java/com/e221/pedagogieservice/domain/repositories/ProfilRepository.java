package com.e221.pedagogieservice.domain.repositories;

import com.e221.pedagogieservice.domain.models.Profil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfilRepository extends JpaRepository<Profil, Long> {

}
