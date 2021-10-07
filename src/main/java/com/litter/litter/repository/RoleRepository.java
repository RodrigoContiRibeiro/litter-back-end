package com.litter.litter.repository;

import com.litter.litter.model.ERole;
import com.litter.litter.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    public Role findByName(ERole name);

    public boolean existsByName(ERole name);
}
