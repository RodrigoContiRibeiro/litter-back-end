package com.litter.litter.controller;

import com.litter.litter.model.Book;
import com.litter.litter.payload.request.book.DeleteBookRequest;
import com.litter.litter.payload.request.book.GetBookRequest;
import com.litter.litter.payload.request.book.SaveBookRequest;
import com.litter.litter.payload.response.MessageResponse;
import com.litter.litter.payload.response.book.GetBookResponse;
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
    }

    @GetMapping("/")
    public ResponseEntity<Object> getById(@RequestBody GetBookRequest getBookRequest) {
        try {
            Book book = bookService.findById(getBookRequest.getId());
            if (book == null) {
                return new ResponseEntity<>(new MessageResponse("Não foi encontrado"), HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(new GetBookResponse(book.getId(), book.getTitle(), book.getAuthor(), book.getSynopsis(), book.getImageUrl()), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(new MessageResponse("Erro"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    public ResponseEntity<MessageResponse> save(@Valid @RequestBody SaveBookRequest saveBookRequest) {
        Book book = new Book(saveBookRequest.getTitle(), saveBookRequest.getAuthor(), saveBookRequest.getSynopsis(), saveBookRequest.getImageUrl());
        bookService.save(book);
        return new ResponseEntity<>(new MessageResponse("Success"), HttpStatus.OK);
    }

    @DeleteMapping("/")
    public ResponseEntity<MessageResponse> deleteById(@RequestBody DeleteBookRequest deleteBookRequest) {
        Book book = bookService.findById(deleteBookRequest.getId());
        if (book != null) {
            if (bookService.deleteById(deleteBookRequest.getId())) {
                return new ResponseEntity<>(new MessageResponse("Sucesso"), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new MessageResponse("Erro"), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>(new MessageResponse("Não foi encontrado"), HttpStatus.NOT_FOUND);
    }
}
