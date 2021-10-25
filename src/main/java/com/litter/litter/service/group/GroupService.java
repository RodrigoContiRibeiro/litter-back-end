package com.litter.litter.service.group;

import com.litter.litter.model.Book;
import com.litter.litter.model.Group;
import com.litter.litter.payload.request.group.DeleteGroupRequest;
import com.litter.litter.payload.response.MessageResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GroupService {
    public boolean save(Group group);
    public Group findById(Long id);
    public List<Group> list();
    public boolean existsByName(String name);
    public boolean deleteById(Long id);

    public ResponseEntity<MessageResponse> validateForDeletion(DeleteGroupRequest deleteGroupRequest);
}
