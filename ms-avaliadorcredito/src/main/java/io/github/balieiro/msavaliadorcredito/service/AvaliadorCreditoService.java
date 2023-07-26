package io.github.balieiro.msavaliadorcredito.service;

import feign.FeignException;
import io.github.balieiro.msavaliadorcredito.controller.exception.DadosClienteNotFoundException;
import io.github.balieiro.msavaliadorcredito.controller.exception.ErroComunicacaoMicroservicesException;
import io.github.balieiro.msavaliadorcredito.entity.CartaoCliente;
import io.github.balieiro.msavaliadorcredito.entity.DadosCliente;
import io.github.balieiro.msavaliadorcredito.entity.SituacaoCliente;
import io.github.balieiro.msavaliadorcredito.infra.clients.CartoesResourceClient;
import io.github.balieiro.msavaliadorcredito.infra.clients.ClienteResourceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AvaliadorCreditoService {

    private final ClienteResourceClient clienteResourceClient;
    private final CartoesResourceClient cartoesResourceClient;

    public SituacaoCliente obterSituacaoCliente(String cpf) throws DadosClienteNotFoundException, ErroComunicacaoMicroservicesException {
        //obterDadosCliente - MS-CLIENTES
        //obter cartoes do cliente - MS-CARTOES
        try {
            ResponseEntity<DadosCliente> dadosClienteResponse = clienteResourceClient.dadosCliente(cpf);
            ResponseEntity<List<CartaoCliente>> cartoesResponse = cartoesResourceClient.getCartoesByCliente(cpf);

            return SituacaoCliente
                    .builder()
                    .cliente(dadosClienteResponse.getBody())
                    .cartoes(cartoesResponse.getBody())
                    .build();
        }catch (FeignException.FeignClientException e){
            int status =  e.status();
            if(HttpStatus.NOT_FOUND.value() == status){
                throw new DadosClienteNotFoundException();
            }
            throw new ErroComunicacaoMicroservicesException(e.getMessage(), status);
        }
    }
}
