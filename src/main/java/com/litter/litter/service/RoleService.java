package com.litter.litter.service;

import com.litter.litter.model.ERole;
import com.litter.litter.model.Role;

public interface RoleService {
    public Role save(Role role);

    public Role findByName(ERole name);

    public boolean existsByName(ERole name);
}
