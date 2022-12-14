package com.example.clientservice.controller;

import com.example.clientservice.entity.Client;
import com.example.clientservice.model.Book;
import com.example.clientservice.model.Laptop;
import com.example.clientservice.service.ClientService;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/saveLaptop")
    public Laptop saveLaptopByClient(@RequestBody Laptop laptop){
        return clientService.saveLaptopByClient(laptop);
    }

    @GetMapping("/findLaptopsByClient/{id}")
    public List<Laptop> findLaptopByClient(@PathVariable Integer id){
        return clientService.findLaptopByClient(id);
    }

    @PostMapping("/saveBook")
    public Book saveBookByClient(@RequestBody Book book){
        return clientService.saveBookByClient(book);
    }

    @GetMapping("/findBookByClient/{id}")
    public List<Book> findBookByClient(@PathVariable Integer id){
        return clientService.findBookByClient(id);
    }

    @GetMapping("/findProductsByClient/{id}")
    public Map<String, Object> findProductsByClient(@PathVariable Integer id){
        return clientService.findProductsByClient(id);
    }
}
