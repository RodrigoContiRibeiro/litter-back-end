package com.litter.litter.service.group;

import com.litter.litter.model.Book;
import com.litter.litter.model.Group;
import com.litter.litter.model.User;
import com.litter.litter.payload.request.group.DeleteGroupRequest;
import com.litter.litter.payload.response.MessageResponse;
import com.litter.litter.repository.group.GroupRepository;
import com.litter.litter.util.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public Group findById(Long id) {
        return groupRepository.findById(id).get();
    }

    @Override
    public List<Group> list() {
        return new MyUtils().removePasswordFromGroupList(groupRepository.findAll());
    }

    @Override
    public boolean existsByName(String name) {
        return groupRepository.existsByName(name);
    }

    @Override
    public boolean deleteById(Long id) {
        try {
            groupRepository.deleteById(id);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public ResponseEntity<MessageResponse> validateForDeletion(DeleteGroupRequest deleteGroupRequest) {
        Group group = findById(deleteGroupRequest.getId());
        if (group.getCreator().getUsername().equals(deleteGroupRequest.getUsername())) {
            if (deleteById(deleteGroupRequest.getId())) {
                return new ResponseEntity<>(new MessageResponse("Sucesso"), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new MessageResponse("Error"), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(new MessageResponse("Não foi permitido deletar"), HttpStatus.UNAUTHORIZED);
        }
    }
}
