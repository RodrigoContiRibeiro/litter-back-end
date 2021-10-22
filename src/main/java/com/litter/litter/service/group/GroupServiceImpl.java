package com.litter.litter.service.group;

import com.litter.litter.model.Book;
import com.litter.litter.model.Group;
import com.litter.litter.model.User;
import com.litter.litter.repository.group.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {
    @Autowired
    GroupRepository groupRepository;

    @Override
    public boolean save(Group group) {
        groupRepository.save(group);
        return false;
    }

    @Override
    public List<Group> list() {
        List<Group> groups = groupRepository.findAll().stream().map(group -> group);
        //TODO Remove password from object
        return groupRepository.findAll();
    }

    @Override
    public boolean existsByName(String name) {
        return groupRepository.existsByName(name);
    }
}
