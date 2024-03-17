package com.bank.itau.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.bank.itau.controller.dto.TransacaoRequest;
import com.bank.itau.exception.BacenOutOfServiceException;
import com.bank.itau.exception.ContaInativaException;
import com.bank.itau.exception.ContaNotFoundException;
import com.bank.itau.exception.LimiteDiarioExcedidoException;
import com.bank.itau.exception.LimiteIndisponivelException;
import com.bank.itau.usecase.NotificarBacenUseCase;

@SpringBootTest
public class ContaServiceIntegrationTest {

	@Autowired
	private ContaService contaService;

	@MockBean
	private NotificarBacenUseCase notificarBacenUseCase;

	@Test
    public void testEfetuarTransferencia() {
        TransacaoRequest transacao = TransacaoRequest.builder()
        		.agenciaOrigem(1)
        		.contaOrigem(1)
        		.agenciaDestino(1)
        		.contaDestino(2)
        		.valor(200)
        		.build();

        doNothing().when(notificarBacenUseCase).notificarTransacao(transacao);

        // Execução e verificação do método real
        Assertions.assertDoesNotThrow(() -> contaService.efetuarTransferencia(transacao));
	}

	@Test
    public void testEfetuarTransferenciaErroBacen() {
		
		TransacaoRequest transacao = TransacaoRequest.builder()
        		.agenciaOrigem(2)
        		.contaOrigem(2)
        		.agenciaDestino(1)
        		.contaDestino(2)
        		.valor(100)
        		.build();

		doThrow(new BacenOutOfServiceException("Serviço do Bacen fora do ar"))
			.when(notificarBacenUseCase)
			.notificarTransacao(transacao);
		
        Assertions.assertThrows(BacenOutOfServiceException.class, () -> contaService.efetuarTransferencia(transacao));
	}


	@Test
    public void testTransferenciaContaDesativada() {
        TransacaoRequest transacao = TransacaoRequest.builder()
        		.agenciaOrigem(2)
        		.contaOrigem(1)
        		.agenciaDestino(1)
        		.contaDestino(2)
        		.valor(200)
        		.build();

        Assertions.assertThrows(ContaInativaException.class, () -> contaService.efetuarTransferencia(transacao));

    }

	@Test
    public void testTransferenciaContaInexistente() {
        TransacaoRequest transacao = TransacaoRequest.builder()
        		.agenciaOrigem(22)
        		.contaOrigem(1)
        		.agenciaDestino(1)
        		.contaDestino(2)
        		.valor(200)
        		.build();

        Assertions.assertThrows(ContaNotFoundException.class, () -> contaService.efetuarTransferencia(transacao));

    }

	@Test
    public void testTransferenciaContaLimiteIndisponivel() {
        TransacaoRequest transacao = TransacaoRequest.builder()
        		.agenciaOrigem(1)
        		.contaOrigem(2)
        		.agenciaDestino(1)
        		.contaDestino(1)
        		.valor(600)
        		.build();

        Assertions.assertThrows(LimiteIndisponivelException.class, () -> contaService.efetuarTransferencia(transacao));

    }

	@Test
    public void testTransferenciaContaLimiteDiárioAtingido() {
        TransacaoRequest transacao = TransacaoRequest.builder()
        		.agenciaOrigem(2)
        		.contaOrigem(2)
        		.agenciaDestino(1)
        		.contaDestino(1)
        		.valor(500)
        		.build();

        Assertions.assertThrows(LimiteDiarioExcedidoException.class, () -> contaService.efetuarTransferencia(transacao));

    }
}