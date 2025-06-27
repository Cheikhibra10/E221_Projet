package com.e221.pedagogieservice.domain.repositories;

import com.e221.pedagogieservice.domain.models.Mention;
import com.e221.pedagogieservice.domain.models.MentionUE;
import com.e221.pedagogieservice.domain.models.UE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MentionUeRepository extends JpaRepository<MentionUE, Long> {

}
