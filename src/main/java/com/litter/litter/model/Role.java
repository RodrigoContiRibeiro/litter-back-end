package com.litter.litter.model;

import javax.persistence.*;

@Entity(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ERole rolename;

   public Role() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ERole getRolename() {
        return rolename;
    }

    public void setRolename(ERole rolename) {
        this.rolename = rolename;
    }
}
