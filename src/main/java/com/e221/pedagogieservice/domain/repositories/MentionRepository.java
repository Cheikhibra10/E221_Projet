package com.e221.pedagogieservice.domain.repositories;

import com.cheikh.commun.core.GenericRepository;
import com.e221.pedagogieservice.domain.models.Mention;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MentionRepository extends GenericRepository<Mention> {

}
