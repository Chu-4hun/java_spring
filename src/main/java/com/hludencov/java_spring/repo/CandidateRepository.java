package com.hludencov.java_spring.repo;

import com.hludencov.java_spring.models.Candidate_info;
import org.springframework.data.repository.CrudRepository;

public interface CandidateRepository extends CrudRepository<Candidate_info, Long> {
}
