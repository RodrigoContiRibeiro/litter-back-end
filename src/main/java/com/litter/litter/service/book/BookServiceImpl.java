package com.litter.litter.service.book;

import com.litter.litter.model.Book;
import com.litter.litter.repository.book.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookRepository bookRepository;

    @Override
    public boolean save(Book book) {
        bookRepository.save(book);
        return true;
    }
}
