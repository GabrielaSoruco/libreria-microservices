package com.example.clientservice.service;

import com.example.clientservice.entity.Client;
import com.example.clientservice.exceptions.DataNotFound;
import com.example.clientservice.feignclients.BookFeignClient;
import com.example.clientservice.feignclients.LaptopFeignClient;
import com.example.clientservice.model.Book;
import com.example.clientservice.model.Laptop;
import com.example.clientservice.repository.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final LaptopFeignClient laptopFeignClient;

    private final BookFeignClient bookFeignClient;

    public ClientService(ClientRepository clientRepository, LaptopFeignClient laptopFeignClient, BookFeignClient bookFeignClient){
        this.clientRepository = clientRepository;
        this.laptopFeignClient = laptopFeignClient;
        this.bookFeignClient = bookFeignClient;
    }

    public List<Client> findAllClients(){
        return clientRepository.findAll();
    }

    public Client findClientById(Integer id){
        return clientRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("Client not found"));
    }

    public Client saveClient(Client client){
        return clientRepository.save(client);
    }

    public Client updateClient(Client client){
        Client clientToUpdate = clientRepository.findById(client.getId())
                .orElseThrow(()-> new NoSuchElementException("Client not found"));
        clientToUpdate.setId(client.getId());
        clientToUpdate.setName(client.getName());
        clientToUpdate.setSurname(client.getSurname());
        clientToUpdate.setEmail(client.getEmail());
        return clientRepository.save(clientToUpdate);
    }

    public String deleteClientById(Integer id){
        Optional<Client> client = clientRepository.findById(id);
        if (client.isPresent()){
            clientRepository.deleteById(id);
            return "The Client has been deleted";
        }
        return "Client not found";
    }

    public Book saveBookByClient(Book book){
        return bookFeignClient.saveBook(book);
    }

    public List<Book> findBookByClient(Integer id){
        return bookFeignClient.findByClientId(id);
    }

    public Laptop saveLaptopByClient(Laptop laptop){
        return laptopFeignClient.saveLaptop(laptop);
    }

    public List<Laptop> findLaptopByClient(Integer id){
        return laptopFeignClient.findByClientId(id);
    }

    public Map<String, Object> findProductsByClient(Integer id){
        Map<String, Object> clientProducts = new HashMap<>();
        Optional<Client> client = clientRepository.findById(id);
        if (client.isPresent()){
            List<Book> books = bookFeignClient.findByClientId(id);
            List<Laptop> laptops = laptopFeignClient.findByClientId(id);
            clientProducts.put("Client", client);
            clientProducts.put("Books", books);
            clientProducts.put("Laptops", laptops);
            return clientProducts;
        }
        throw new DataNotFound("Client not found id: " + id);
    }
}
