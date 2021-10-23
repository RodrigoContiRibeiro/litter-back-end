package com.litter.litter.util;

import com.litter.litter.model.Group;
import com.litter.litter.model.User;

import java.util.ArrayList;
import java.util.List;

public class MyUtils {
    public List<Group> removePasswordFromGroupList(List<Group> groups){
        List<Group> groupsWithoutPassword = new ArrayList<>();
        for (Group group : groups) {
            group.getCreator().setPassword(null);
            groupsWithoutPassword.add(group);
        }
        return groupsWithoutPassword;
    }

    public List<User> removePasswordFromUserList(List<User> users) {
        List<User> usersWithoutPassword = new ArrayList<>();
        for (User user : users) {
            user.setPassword(null);
            usersWithoutPassword.add(user);
        }
        return usersWithoutPassword;
    }
}
