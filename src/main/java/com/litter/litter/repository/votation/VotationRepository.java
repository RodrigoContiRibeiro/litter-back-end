package com.litter.litter.repository.votation;

import com.litter.litter.model.Votation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotationRepository extends JpaRepository<Votation, Long> {
}
