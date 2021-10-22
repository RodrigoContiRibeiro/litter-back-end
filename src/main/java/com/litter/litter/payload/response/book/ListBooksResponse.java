package com.litter.litter.payload.response.book;

import com.litter.litter.model.Book;

import java.util.List;

public class ListBooksResponse {
    private List<Book> books;

    public ListBooksResponse(List<Book> books) {
        this.books = books;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
