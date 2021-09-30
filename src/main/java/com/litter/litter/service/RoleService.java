package com.litter.litter.service;

import com.litter.litter.model.ERole;
import com.litter.litter.model.Role;

public interface RoleService {
    public Role findByRolename(ERole name);
}
