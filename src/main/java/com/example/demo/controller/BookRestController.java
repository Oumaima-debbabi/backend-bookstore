package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Book;
import com.example.demo.repository.BookRepository;
import com.example.demo.services.BookService;
@RestController
@RequestMapping("/bookstore/")
public class BookRestController {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookService bookService;

    @GetMapping("")
    public List<Book> getAll() {
        return bookService.getAll();
    }

    @GetMapping("book/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @PostMapping("book/add")
    public Book addBook(@RequestBody @Validated Book b, BindingResult result) {
        if (result.hasErrors())
            System.err.println(result.getAllErrors());
        return bookService.addBook(b);
    }

    @DeleteMapping("book/{id}/delete")
    public Book deleteBook(@PathVariable Long id) {
        return bookService.deleteBook(id);
    }

    @PutMapping("book/{id}/modify")
    public void updateBook(@RequestBody @Validated Book b, @PathVariable Long id,
                           BindingResult result) {
        if (result.hasErrors())
            System.err.println(result.getAllErrors());
        bookService.updateBook(b, id);
    }
}
