package io.github.balieiro.msavaliadorcredito.controller;

import io.github.balieiro.msavaliadorcredito.controller.exception.DadosClienteNotFoundException;
import io.github.balieiro.msavaliadorcredito.controller.exception.ErroComunicacaoMicroservicesException;
import io.github.balieiro.msavaliadorcredito.entity.SituacaoCliente;
import io.github.balieiro.msavaliadorcredito.service.AvaliadorCreditoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("avaliacoes-credito")
@RequiredArgsConstructor
@Slf4j
public class AvaliadorCreditoController {

    private final AvaliadorCreditoService avaliadorCreditoService;

    @GetMapping
    public String status(){
        return "Ok";
    }
    @GetMapping(params = "cpf")
    public ResponseEntity consultaSituacaoCliente(@RequestParam("cpf") String cpf){
        log.info("Obtendo o status do microservice de Avaliador de Crédito - Contulta Situação Cliente");
        try {
            SituacaoCliente situacaoCliente = avaliadorCreditoService.obterSituacaoCliente(cpf);
            return ResponseEntity.ok(situacaoCliente);
        } catch (DadosClienteNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ErroComunicacaoMicroservicesException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }
}
