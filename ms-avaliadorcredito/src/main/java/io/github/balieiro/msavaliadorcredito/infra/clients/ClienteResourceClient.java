package io.github.balieiro.msavaliadorcredito.infra.clients;

import io.github.balieiro.msavaliadorcredito.entity.DadosCliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "ms-clientes", url = "http://localhost:8082/clientes")
public interface ClienteResourceClient {
    @GetMapping(params = "cpf")
    ResponseEntity<DadosCliente> dadosCliente(@RequestParam("cpf") String cpf);
}
