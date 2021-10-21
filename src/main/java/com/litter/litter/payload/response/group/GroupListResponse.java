package com.litter.litter.payload.response.group;

import com.litter.litter.model.Book;
import com.litter.litter.model.Group;

import java.util.List;

public class GroupListResponse {
    private List<Group> books;

    public GroupListResponse(List<Group> books) {
        this.books = books;
    }

    public List<Group> getBooks() {
        return books;
    }

    public void setBooks(List<Group> books) {
        this.books = books;
    }
}
