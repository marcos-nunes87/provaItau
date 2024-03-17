package com.bank.itau.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.itau.controller.dto.TransacaoRequest;
import com.bank.itau.service.ContaService;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(value="/contas")
public class ContaRestController {
    private final ContaService contaService;

    public ContaRestController(ContaService contaService) {
        this.contaService = contaService;
    }

    @GetMapping("/{agencia}/{conta}/saldo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description ="Retorna o saldo da conta. No formato Saldo da conta: R$XXX.XX Com limite disponível de R$XXX.XX Total(Saldo+Limite) R$XXX.XX"),
            @ApiResponse(responseCode ="404", description="Conta não encontrada.")
        })
    public ResponseEntity<String> consultarSaldoConta(@PathVariable("agencia") Integer agencia, @PathVariable("conta") Integer conta) {
        String saldo = contaService.consultarSaldoConta(agencia,conta);
    	return ResponseEntity.ok(saldo);
    }

    @PostMapping("/transferencia")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description ="Transferência realizada."),
            @ApiResponse(responseCode ="400", description="O serviço do BACEN está indisponível. Notificação não enviada."),
            @ApiResponse(responseCode ="404", description="Conta não encontrada."),
            @ApiResponse(responseCode ="422", description="Conta inativa ou não encontrada."),
            @ApiResponse(responseCode ="422", description="Limite diário de transferência excedido."),
            @ApiResponse(responseCode ="422", description="Limite insuficiente para efetuar a transferência.")
        })
    public ResponseEntity<String> efetuarTransferencia(@RequestBody TransacaoRequest transacao) {
    	contaService.efetuarTransferencia(transacao);
		return ResponseEntity.created(null).body("Transferência realizada.");
    }

}
