package com.example.laptopservice.controller;

import com.example.laptopservice.entity.Laptop;
import com.example.laptopservice.service.LaptopService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/laptops")
public class LaptopController {

    private final LaptopService laptopService;

    public LaptopController(LaptopService laptopService) {
        this.laptopService = laptopService;
    }

    @GetMapping
    public List<Laptop> findAllLaptop() {
        return laptopService.findAllLaptop();
    }

    @GetMapping("/{id}")
    public Laptop findLaptopById(@PathVariable Integer id){
        return laptopService.findLaptopById(id);
    }

    @PostMapping
    public Laptop saveLaptop(@RequestBody Laptop laptop){
        return laptopService.saveLaptop(laptop);
    }

    @PutMapping("/updateLaptop")
    public Laptop updateLaptop(@RequestBody Laptop laptop){
        return laptopService.updateLaptop(laptop);
    }

    @DeleteMapping("/deleteLaptop/{id}")
    public String deleteLaptop(@PathVariable Integer id){
        return laptopService.deleteLaptop(id);
    }
}
