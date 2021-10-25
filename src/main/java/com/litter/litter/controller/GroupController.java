package com.litter.litter.controller;

import com.litter.litter.model.Book;
import com.litter.litter.model.Group;
import com.litter.litter.model.User;
import com.litter.litter.payload.request.group.DeleteGroupRequest;
import com.litter.litter.payload.request.group.GetGroupRequest;
import com.litter.litter.payload.request.group.GroupSaveRequest;
import com.litter.litter.payload.response.MessageResponse;
import com.litter.litter.payload.response.group.GetGroupResponse;
import com.litter.litter.payload.response.group.GroupListResponse;
import com.litter.litter.service.book.BookServiceImpl;
import com.litter.litter.service.group.GroupServiceImpl;
import com.litter.litter.service.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/group")
public class GroupController {
    @Autowired
    GroupServiceImpl groupService;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    BookServiceImpl bookService;

    @GetMapping("/list")
    public GroupListResponse list() {
        return new GroupListResponse(groupService.list());
    }

    @PostMapping("/")
    public ResponseEntity<MessageResponse> save(@RequestBody GroupSaveRequest groupSaveRequest) {
        try {
            boolean alreadyExists = groupService.existsByName(groupSaveRequest.getName());
            if (alreadyExists) {
                return new ResponseEntity<>(new MessageResponse("Nome de Grupo já existe"), HttpStatus.BAD_REQUEST);
            }

            User creator = userService.findById(groupSaveRequest.getCreator());
            boolean isUpdate = groupSaveRequest.getId() != null;

            boolean hasBook = groupSaveRequest.getCurrentBook() != null;
            if (hasBook) {
                Book currentBook = bookService.findById(groupSaveRequest.getCurrentBook());
                if (isUpdate) {
                    groupService.save(new Group(groupSaveRequest.getId(), creator, groupSaveRequest.getName(), groupSaveRequest.getDescription(), currentBook));
                } else {
                    groupService.save(new Group(creator, groupSaveRequest.getName(), groupSaveRequest.getDescription(), currentBook));
                }
            } else {
                if (isUpdate) {
                    groupService.save(new Group(groupSaveRequest.getId(), creator, groupSaveRequest.getName(), groupSaveRequest.getDescription()));
                } else {
                    groupService.save(new Group(creator, groupSaveRequest.getName(), groupSaveRequest.getDescription()));
                }
            }
            return new ResponseEntity<>(new MessageResponse("Sucesso"), HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(new MessageResponse("Erro"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/")
    public ResponseEntity getById(@RequestBody GetGroupRequest getGroupRequest) {
        Group group = groupService.findById(getGroupRequest.getId());
        if (group != null) {
            return new ResponseEntity(new GetGroupResponse(group.getId(), group.getCreator(), group.getName(), group.getDescription(), group.getCurrentBook()), HttpStatus.OK);
        }
        return new ResponseEntity(new MessageResponse("Não foi encontrado"), HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/")
    public ResponseEntity<MessageResponse> delete(@RequestBody DeleteGroupRequest deleteGroupRequest) {
        return groupService.validateForDeletion(deleteGroupRequest);
    }
}
