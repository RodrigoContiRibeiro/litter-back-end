package com.litter.litter.service.group;

import com.litter.litter.model.Group;
import com.litter.litter.repository.group.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl implements GroupService {
    @Autowired
    GroupRepository groupRepository;

    @Override
    public boolean save(Group group) {
        groupRepository.save(group);
        return false;
    }
}
