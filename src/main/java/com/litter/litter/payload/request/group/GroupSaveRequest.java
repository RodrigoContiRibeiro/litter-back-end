package com.litter.litter.payload.request.group;

public class GroupSaveRequest {
    private Long id;
    private Long creator;
    private String name;
    private String description;
    private Long currentBook;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreator() {
        return creator;
    }

    public void setCreator(Long creator) {
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

    public Long getCurrentBook() {
        return currentBook;
    }

    public void setCurrentBook(Long currentBook) {
        this.currentBook = currentBook;
    }
}
