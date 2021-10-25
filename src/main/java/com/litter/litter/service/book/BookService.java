package com.litter.litter.service.book;

import com.litter.litter.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    public boolean save(Book book);
    public Book findById(Long id);
    public List<Book> findAll();
    public boolean deleteById(Long id);
}
