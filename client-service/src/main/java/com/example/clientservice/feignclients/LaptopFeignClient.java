package com.example.clientservice.feignclients;

import com.example.clientservice.model.Laptop;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "laptop-service")
public interface LaptopFeignClient {

    @PostMapping
    Laptop saveLaptop(@RequestBody Laptop laptop);

    @GetMapping("/byClient/{id}")
    List<Laptop> findByClientId(@PathVariable Integer id);
}
