package com.litter.litter.controller;

import com.litter.litter.model.Book;
import com.litter.litter.model.Votation;
import com.litter.litter.payload.request.book.SaveBookRequest;
import com.litter.litter.payload.response.MessageResponse;
import com.litter.litter.service.book.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/book")
public class BookController {
    @Autowired
    BookServiceImpl bookService;

    @PostMapping("/")
    public MessageResponse save(@Valid @RequestBody SaveBookRequest saveBookRequest) {
        System.out.println(saveBookRequest);
        Book book = new Book(saveBookRequest.getTitle(), saveBookRequest.getAuthor(), saveBookRequest.getSynopsis(), saveBookRequest.getImageUrl());
        bookService.save(book);
        return new MessageResponse("Legal");
    }
}
