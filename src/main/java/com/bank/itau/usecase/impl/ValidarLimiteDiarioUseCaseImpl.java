package com.bank.itau.usecase.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.bank.itau.controller.dto.TransacaoRequest;
import com.bank.itau.domain.Transacao;
import com.bank.itau.exception.LimiteDiarioExcedidoException;
import com.bank.itau.repository.TransacaoRepository;
import com.bank.itau.usecase.ValidarLimiteDiarioUseCase;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ValidarLimiteDiarioUseCaseImpl implements ValidarLimiteDiarioUseCase {

	private final TransacaoRepository transacaoRepository;

    public ValidarLimiteDiarioUseCaseImpl(TransacaoRepository transacaoRepository) {
        this.transacaoRepository = transacaoRepository;
    }

	@Override
	public void validarLimiteDiario(TransacaoRequest transacao) {
		log.info("validarLimiteDiario - " + transacao);
		LocalDate hoje = LocalDate.now();

        Optional<Double> totalTransacoes = transacaoRepository.sumValorByData(hoje);

        if (totalTransacoes.isPresent()) {
            double somaAtual = totalTransacoes.get();
            if (somaAtual + transacao.getValor() > 1000) {
                throw new LimiteDiarioExcedidoException("Limite diário de transferência excedido.");
            }
        } else {
            if (transacao.getValor() > 1000) {
                throw new LimiteDiarioExcedidoException("Limite diário de transferência excedido.");
            }
        }
	}
}
