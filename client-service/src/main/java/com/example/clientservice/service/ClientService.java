package com.example.clientservice.service;

import com.example.clientservice.entity.Client;
import com.example.clientservice.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
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

}
