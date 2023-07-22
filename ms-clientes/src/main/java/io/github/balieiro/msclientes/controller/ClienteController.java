package io.github.balieiro.msclientes.controller;

import io.github.balieiro.msclientes.controller.representation.ClienteSaveRequest;
import io.github.balieiro.msclientes.entity.Client;
import io.github.balieiro.msclientes.service.ClienteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("clientes")
@Slf4j
public class ClienteController {

    @GetMapping
    public String status(){

        log.info("Obtendo o status do microservice de clientes");
        return "Ok";
    }
    private ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity save(@RequestBody ClienteSaveRequest request){
        Client client = request.toModel();
        clienteService.save(client);
        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("cpf={cpf}")
                .buildAndExpand(client.getCpf())
                .toUri();
        return ResponseEntity.created(headerLocation).build();
    }

    @GetMapping(params = "cpf")
    public ResponseEntity dadosCliente(@RequestParam String cpf){
        var cliente = clienteService.getByCPF(cpf);
        if(cliente.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliente);
    }
}
