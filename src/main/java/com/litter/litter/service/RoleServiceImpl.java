package com.litter.litter.service;

import com.litter.litter.model.ERole;
import com.litter.litter.model.Role;
import com.litter.litter.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role findByName(ERole name) {
        return roleRepository.findByName(name);
    }

    @Override
    public boolean existsByName(ERole name) {
        return roleRepository.existsByName(name);
    }
}
