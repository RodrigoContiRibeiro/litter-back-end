package com.litter.litter.controller;

import com.litter.litter.model.Book;
import com.litter.litter.payload.request.book.SaveBookRequest;
import com.litter.litter.payload.response.MessageResponse;
import com.litter.litter.payload.response.book.ListBooksResponse;
import com.litter.litter.service.book.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/book")
public class BookController {
    @Autowired
    BookServiceImpl bookService;

    @GetMapping("/list")
    public ResponseEntity<ListBooksResponse> list() {
        List<Book> list = bookService.findAll();
        return new ResponseEntity<>(new ListBooksResponse(list), HttpStatus.OK);
    };

    @PostMapping("/")
    public ResponseEntity<MessageResponse> save(@Valid @RequestBody SaveBookRequest saveBookRequest) {
        Book book = new Book(saveBookRequest.getTitle(), saveBookRequest.getAuthor(), saveBookRequest.getSynopsis(), saveBookRequest.getImageUrl());
        bookService.save(book);
        return new ResponseEntity<>(new MessageResponse("Success"), HttpStatus.OK);
    }
}
