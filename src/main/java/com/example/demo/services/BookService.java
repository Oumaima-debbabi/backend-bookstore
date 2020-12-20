package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Book;
import com.example.demo.repository.BookRepository;


@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public List<Book> getAll(){
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) throws ResourceNotFoundException {
        return bookRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("The book " +
                "with the ID "+id+" does not exist"));
    }

    public Book addBook(Book b) {
        return bookRepository.save(b);
    }

    public Book deleteBook(Long id) throws ResourceNotFoundException {
        Book b = bookRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("The command " +
                "line with the ID "+id+" does not exist"));
        bookRepository.deleteById(id);
        return b;
    }

    public void updateBook(Book b,  Long id) throws ResourceNotFoundException {
        bookRepository.findById(id).map(book -> {
            book.setAuthor(b.getAuthor());
            book.setPrice(b.getPrice());
            book.setReleaseDate(b.getReleaseDate());
            book.setTitle(b.getTitle());
            return bookRepository.save(book);
        }).orElseThrow(()-> new ResourceNotFoundException("The book " +
                        "with the ID "+id+" does not exist"));
    }

}
