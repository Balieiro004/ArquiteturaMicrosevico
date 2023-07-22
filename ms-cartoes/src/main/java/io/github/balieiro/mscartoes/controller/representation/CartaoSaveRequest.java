package io.github.balieiro.mscartoes.controller.representation;

import io.github.balieiro.mscartoes.entity.Cartao;
import io.github.balieiro.mscartoes.entity.enums.BandeiraCartao;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartaoSaveRequest {

    private String nome;
    private BandeiraCartao bandeira;
    private BigDecimal renda;
    private BigDecimal limite;

    public Cartao toModel(){
        return new Cartao(nome, bandeira, renda, limite);
    }
}
