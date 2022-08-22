package com.example.laptopservice.service;

import com.example.laptopservice.entity.Laptop;
import com.example.laptopservice.repository.LaptopRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class LaptopService {

    private final LaptopRepository laptopRepository;

    public LaptopService(LaptopRepository laptopRepository){
        this.laptopRepository = laptopRepository;
    }

    public List<Laptop> findAllLaptop(){
        return laptopRepository.findAll();
    }

    public Laptop findLaptopById(Integer id){
        return laptopRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("Laptop not found"));
    }

    public Laptop saveLaptop(Laptop laptop){
        return laptopRepository.save(laptop);
    }

    public Laptop updateLaptop(Laptop laptop){
        Laptop laptopToUpdate = this.findLaptopById(laptop.getId());
        laptopToUpdate.setBrand(laptop.getBrand());
        laptopToUpdate.setModel(laptop.getModel());
        laptopToUpdate.setPrice(laptop.getPrice());
        return laptopRepository.save(laptopToUpdate);
    }

    public String deleteLaptop(Integer id){
        Optional<Laptop> laptop = laptopRepository.findById(id);
        if (laptop.isPresent()){
            laptopRepository.delete(laptop.get());
            return "The Laptop has been deleted";
        }
        return "Laptop not found";
    }
}
