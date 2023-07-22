package io.github.balieiro.mscartoes.service;

import io.github.balieiro.mscartoes.entity.ClienteCartao;
import io.github.balieiro.mscartoes.repository.ClienteCartaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteCartaoService {

    private ClienteCartaoRepository clienteCartaoRepository;

    public ClienteCartaoService(ClienteCartaoRepository clienteCartaoRepository) {
        this.clienteCartaoRepository = clienteCartaoRepository;
    }

    public List<ClienteCartao> listCartoesByCpf(String cpf){
        return clienteCartaoRepository.findByCpf(cpf);
    }
}
