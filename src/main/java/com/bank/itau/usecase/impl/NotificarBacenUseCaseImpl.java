package com.bank.itau.usecase.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.bank.itau.controller.dto.TransacaoRequest;
import com.bank.itau.exception.BacenOutOfServiceException;
import com.bank.itau.usecase.NotificarBacenUseCase;

@Component
public class NotificarBacenUseCaseImpl implements NotificarBacenUseCase {

	@Autowired
    private RestTemplate restTemplate;

	@Override
	public void notificarTransacao(TransacaoRequest transacao) {
		String url = "http://localhost:8080/bacen-api/notify";
        restTemplate.getForObject(url, String.class);
    }

	public void fallbackNotificarBacen(TransacaoRequest transacao, Throwable throwable) {
        // Lógica de fallback em caso de falha
        throw new BacenOutOfServiceException("O serviço do BACEN está indisponível. Notificação não enviada.");
        // Ou então, você pode lançar uma exceção aqui para ser tratada no serviço chamador
    }
}
