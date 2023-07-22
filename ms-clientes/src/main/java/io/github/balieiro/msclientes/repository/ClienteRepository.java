package io.github.balieiro.msclientes.repository;

import io.github.balieiro.msclientes.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByCpf(String cpf);
}
