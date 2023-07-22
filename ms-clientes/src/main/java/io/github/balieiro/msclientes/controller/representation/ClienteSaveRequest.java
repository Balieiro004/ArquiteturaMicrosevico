package io.github.balieiro.msclientes.controller.representation;

import io.github.balieiro.msclientes.entity.Client;
import lombok.Data;

@Data
public class ClienteSaveRequest {

    private String cpf;
    private String nome;
    private Integer idade;

    public Client toModel(){
        return new Client(cpf, nome, idade);
    }
}
