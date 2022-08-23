package com.example.clientservice.feignclients;

import com.example.clientservice.model.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "book-service", url = "http://localhost:8090/books")
public interface BookFeignClient {

    @PostMapping
    Book saveBook(@RequestBody Book book);

    @GetMapping("/byClient/{id}")
    List<Book> findByClientId(@PathVariable Integer id);
}
