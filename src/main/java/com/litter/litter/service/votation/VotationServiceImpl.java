package com.litter.litter.service.votation;

import com.litter.litter.model.Votation;
import com.litter.litter.repository.votation.VotationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VotationServiceImpl implements VotationService{
    @Autowired
    VotationRepository votationRepository;

    @Override
    public boolean save(Votation votation) {
        votationRepository.save(votation);
        return true;
    }
}
