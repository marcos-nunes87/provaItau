package com.bank.itau.usecase.impl;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.bank.itau.domain.Conta;
import com.bank.itau.domain.ContaId;
import com.bank.itau.exception.ContaInativaException;
import com.bank.itau.exception.ContaNotFoundException;
import com.bank.itau.repository.ContaRepository;
import com.bank.itau.usecase.ValidarContaAtivaUseCase;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ValidarContaAtivaUseCaseImpl implements ValidarContaAtivaUseCase{

	private final ContaRepository contaRepository;

    public ValidarContaAtivaUseCaseImpl(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    @Override
	public void validarContaAtiva(Integer agencia, Integer conta) {
    	log.info("validarContaAtiva - Agência:" + agencia + " - Conta: " + conta);
    	Optional<Conta> optionalConta = contaRepository.findByContaId(new ContaId(agencia, conta));
        if (optionalConta.isEmpty()) {
            throw new ContaNotFoundException("Conta não encontrada.");
        }		
        if (!optionalConta.get().isAtivo()) {
            throw new ContaInativaException("Conta inativa.");
        }		
	}

}
