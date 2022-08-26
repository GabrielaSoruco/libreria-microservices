package com.example.clientservice.controller;

import com.example.clientservice.entity.Client;
import com.example.clientservice.model.Book;
import com.example.clientservice.model.Laptop;
import com.example.clientservice.service.ClientService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }

    @GetMapping
    public List<Client> findAllClients(){
        return clientService.findAllClients();
    }

    @GetMapping("/{id}")
    public Client findClientById(@PathVariable Integer id){
        return clientService.findClientById(id);
    }

    @PostMapping
    public Client saveClient(@RequestBody Client client){
        return clientService.saveClient(client);
    }

    @PutMapping("/updateClient")
    public Client updateClient(@RequestBody Client clientToUpdate){
        return clientService.updateClient(clientToUpdate);
    }

    @DeleteMapping("/deleteClient/{id}")
    public String deleteClient(@PathVariable Integer id){
        return clientService.deleteClientById(id);
    }

    @CircuitBreaker(name = "laptopCB", fallbackMethod = "fallbackSaveLaptop")
    @PostMapping("/saveLaptop")
    public ResponseEntity<Laptop> saveLaptopByClient(@RequestBody Laptop laptop){
        return ResponseEntity.ok(clientService.saveLaptopByClient(laptop));
    }

    @CircuitBreaker(name = "laptopCB", fallbackMethod = "fallbackFindLaptopsByClient")
    @GetMapping("/findLaptopsByClient/{id}")
    public ResponseEntity<List<Laptop>> findLaptopByClient(@PathVariable Integer id){
        return ResponseEntity.ok(clientService.findLaptopByClient(id));
    }

    @CircuitBreaker(name = "bookCB", fallbackMethod = "fallbackSaveBookByClient")
    @PostMapping("/saveBook")
    public ResponseEntity<Book> saveBookByClient(@RequestBody Book book){
        return ResponseEntity.ok(clientService.saveBookByClient(book));
    }
    @CircuitBreaker(name = "bookCB", fallbackMethod = "fallbackFindBookByClient")
    @GetMapping("/findBookByClient/{id}")
    public ResponseEntity<List<Book>> findBookByClient(@PathVariable Integer id){
        return ResponseEntity.ok(clientService.findBookByClient(id));
    }

    @CircuitBreaker(name = "productsCB", fallbackMethod = "fallbackFindProductsByClient")
    @GetMapping("/findProductsByClient/{id}")
    public ResponseEntity<Map<String, Object>> findProductsByClient(@PathVariable Integer id){
        return ResponseEntity.ok(clientService.findProductsByClient(id));
    }

    private ResponseEntity<Laptop> fallbackSaveLaptop(@RequestBody Laptop laptop, RuntimeException e){
        return ResponseEntity.ok(new Laptop());
    }

    private ResponseEntity<List<Laptop>> fallbackFindLaptopsByClient(@PathVariable Integer id){
        List<Laptop> laptops = new ArrayList<>();
        return ResponseEntity.ok(laptops);
    }

    private ResponseEntity<Book> fallbackSaveBookByClient(@RequestBody Book book){
        return ResponseEntity.ok(new Book());
    }

    private ResponseEntity<List<Book>> fallbackFindBookByClient(@PathVariable Integer id){
        return ResponseEntity.ok(new ArrayList<>());
    }

    private ResponseEntity<Map<String, Object>> fallbackFindProductsByClient(@PathVariable Integer id){
        return ResponseEntity.ok(new HashMap<>());
    }
}
