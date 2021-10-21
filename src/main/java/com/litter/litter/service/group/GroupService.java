package com.litter.litter.service.group;

import com.litter.litter.model.Book;
import com.litter.litter.model.Group;

import java.util.List;

public interface GroupService {
    public boolean save(Group group);
    public List<Group> list();
    public boolean existsByName(String name);
}
