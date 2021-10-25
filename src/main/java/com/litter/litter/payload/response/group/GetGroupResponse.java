package com.litter.litter.payload.response.group;


import com.litter.litter.model.Book;
import com.litter.litter.model.User;

public class GetGroupResponse {
    private Long id;
    private User creator;
    private String name;
    private String description;
    private Book currentBook;

    public GetGroupResponse() {
    }

    public GetGroupResponse(Long id, User creator, String name, String description, Book currentBook) {
        this.id = id;
        this.creator = creator;
        this.name = name;
        this.description = description;
        this.currentBook = currentBook;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public User getCreator() {
        return creator;
    }
    public void setCreator(User creator) {
        this.creator = creator;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Book getCurrentBook() {
        return currentBook;
    }
    public void setCurrentBook(Book currentBook) {
        this.currentBook = currentBook;
    }
}
