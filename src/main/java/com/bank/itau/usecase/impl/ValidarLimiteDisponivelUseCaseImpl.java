package com.bank.itau.usecase.impl;

import org.springframework.stereotype.Component;

import com.bank.itau.controller.dto.TransacaoRequest;
import com.bank.itau.domain.Conta;
import com.bank.itau.domain.ContaId;
import com.bank.itau.exception.ContaNotFoundException;
import com.bank.itau.exception.LimiteIndisponivelException;
import com.bank.itau.repository.ContaRepository;
import com.bank.itau.usecase.ValidarLimiteDisponivelUseCase;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ValidarLimiteDisponivelUseCaseImpl implements ValidarLimiteDisponivelUseCase {

	private final ContaRepository contaRepository;

    public ValidarLimiteDisponivelUseCaseImpl(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

	@Override
	public void validarLimiteDisponivel(TransacaoRequest transacao) {
		log.info("validarLimiteDisponivel - " + transacao);

        Conta origem = contaRepository.findByContaId(new ContaId(transacao.getAgenciaOrigem(), transacao.getContaOrigem()))
                .orElseThrow(() -> new ContaNotFoundException("Conta de origem não encontrada."));

        double saldoLimiteDisponivel = origem.getSaldo() + origem.getLimite();
        if (saldoLimiteDisponivel < transacao.getValor()) {
            throw new LimiteIndisponivelException("Limite insuficiente para efetuar a transferência.");
        }
	}

}
