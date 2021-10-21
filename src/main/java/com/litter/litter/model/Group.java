package com.litter.litter.model;

import javax.persistence.*;

@Entity(name = "book_group")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "creator_id", referencedColumnName = "id")
    private User creator;

    private String name;

    @Lob
    private String description;

    @OneToOne
    @JoinColumn(name = "currentBook_id", referencedColumnName = "id")
    private Book currentBook;

    public Group(){}

    public Group(Long id, User creator, String name, String description) {
        this.id = id;
        this.creator = creator;
        this.name = name;
        this.description = description;
    }

    public Group(Long id, User creator, String name, String description, Book currentBook) {
        this.id = id;
        this.creator = creator;
        this.name = name;
        this.description = description;
        this.currentBook = currentBook;
    }

    public Group(User creator, String name, String description) {
        this.creator = creator;
        this.name = name;
        this.description = description;
    }

    public Group(User creator, String name, String description, Book currentBook) {
        this.creator = creator;
        this.name = name;
        this.description = description;
        this.currentBook = currentBook;
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
