package com.example.clientservice.feignclients;

import com.example.clientservice.model.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "book-service", path = "/books")
public interface BookFeignClient {

    @PostMapping
    Book saveBook(@RequestBody Book book);

    @GetMapping("/byClient/{id}")
    List<Book> findByClientId(@PathVariable Integer id);
}
