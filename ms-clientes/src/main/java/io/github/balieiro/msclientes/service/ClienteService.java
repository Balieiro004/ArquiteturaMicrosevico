package io.github.balieiro.msclientes.service;

import io.github.balieiro.msclientes.entity.Client;
import io.github.balieiro.msclientes.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    private ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Transactional
    public Client save(Client client){
        return clienteRepository.save(client);
    }

    public Optional<Client> getByCPF(String cpf){
        return clienteRepository.findByCpf(cpf);
    }
}
