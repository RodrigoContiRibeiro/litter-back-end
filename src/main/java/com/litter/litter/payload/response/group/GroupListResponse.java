package com.litter.litter.payload.response.group;

import com.litter.litter.model.Book;
import com.litter.litter.model.Group;

import java.util.List;

public class GroupListResponse {
    private List<Group> groups;

    public GroupListResponse(List<Group> groups) {
        this.groups = groups;
    }

    public List<Group> getGroups()   {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
}
