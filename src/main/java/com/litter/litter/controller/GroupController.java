package com.litter.litter.controller;

import com.litter.litter.payload.request.group.GroupSaveRequest;
import com.litter.litter.payload.response.MessageResponse;
import com.litter.litter.payload.response.group.GroupListResponse;
import com.litter.litter.service.group.GroupServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/group")
public class GroupController {
    @Autowired
    GroupServiceImpl groupService;

    @GetMapping("/")
    public GroupListResponse list(){
        return new GroupListResponse(groupService.list());
    };

    @PostMapping("/")
    public MessageResponse save(@RequestBody GroupSaveRequest groupSaveRequest) {
        groupService.save(groupSaveRequest);
        return new MessageResponse("Salvou");
    }

}
