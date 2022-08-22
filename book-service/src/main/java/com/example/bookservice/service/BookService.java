package com.example.bookservice.service;

import com.example.bookservice.entity.Book;
import com.example.bookservice.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public List<Book> findAllBook(){
        return bookRepository.findAll();
    }

    public Book findBookById(Integer id){
        return bookRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Book not found"));
    }

    public Book saveBook(Book book){
        return bookRepository.save(book);
    }

    public Book updateBook(Book newBook){
        Book bookToUpdate = bookRepository.findById(newBook.getId()).orElseThrow(()-> new NoSuchElementException("Book not found"));
        bookToUpdate.setId(newBook.getId());
        bookToUpdate.setName(newBook.getName());
        bookToUpdate.setAuthor(newBook.getAuthor());
        bookToUpdate.setPrice(newBook.getPrice());
        return bookRepository.save(bookToUpdate);
    }

    public String deleteBook(Integer id){
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()){
            bookRepository.delete(book.get());
            return "The Book has been deleted";
        }
        return "Book not found";
    }
}
