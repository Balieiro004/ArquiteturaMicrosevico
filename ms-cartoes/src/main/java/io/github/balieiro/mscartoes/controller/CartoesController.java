package io.github.balieiro.mscartoes.controller;

import io.github.balieiro.mscartoes.controller.representation.CartaoSaveRequest;
import io.github.balieiro.mscartoes.controller.representation.CartoesPorClienteResponse;
import io.github.balieiro.mscartoes.entity.Cartao;
import io.github.balieiro.mscartoes.entity.ClienteCartao;
import io.github.balieiro.mscartoes.service.CartaoService;
import io.github.balieiro.mscartoes.service.ClienteCartaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("cartoes")
public class CartoesController {

    private CartaoService cartaoService;
    private ClienteCartaoService clienteCartaoService;

    public CartoesController(CartaoService cartaoService, ClienteCartaoService clienteCartaoService) {
        this.cartaoService = cartaoService;
        this.clienteCartaoService = clienteCartaoService;
    }

    @GetMapping
    public String status(){
        return "Ok";
    }

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody CartaoSaveRequest request){
        Cartao cartao = request.toModel();
        cartaoService.save(cartao);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(params = "renda")
    public ResponseEntity<List<Cartao>> getCartoesRendaAteh(@RequestParam(name = "renda") Long renda){
        List<Cartao> list = cartaoService.getCartoesRendaMenorIgual(renda);
        return ResponseEntity.ok(list);
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<List<CartoesPorClienteResponse>> getCartoesByCliente(@RequestParam("cpf") String cpf){
        List<ClienteCartao> lista = clienteCartaoService.listCartoesByCpf(cpf);
        List<CartoesPorClienteResponse> resultList = lista.stream()
                .map(CartoesPorClienteResponse::fromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resultList);
    }
}
