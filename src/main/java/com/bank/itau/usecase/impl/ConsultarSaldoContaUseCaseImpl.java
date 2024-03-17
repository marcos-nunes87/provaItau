package com.bank.itau.usecase.impl;

import java.text.DecimalFormat;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.bank.itau.domain.Conta;
import com.bank.itau.domain.ContaId;
import com.bank.itau.exception.ContaNotFoundException;
import com.bank.itau.repository.ContaRepository;
import com.bank.itau.usecase.ConsultarSaldoContaUseCase;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ConsultarSaldoContaUseCaseImpl implements ConsultarSaldoContaUseCase {

	private final ContaRepository contaRepository;

	public ConsultarSaldoContaUseCaseImpl(ContaRepository contaRepository) {
		this.contaRepository = contaRepository;
	}

	@Override
	public String consultarSaldo(Integer agencia, Integer conta) {
		log.info("consultarSaldo - Agência:\" + agencia + \" - Conta: \" + conta");
		Optional<Conta> optionalConta = contaRepository.findByContaId(new ContaId(agencia, conta));

		if (optionalConta.isPresent()) {
			Conta contaEncontrada = optionalConta.get();
			DecimalFormat df = new DecimalFormat("#,##0.00");
			return "Saldo da conta: R$" + df.format(contaEncontrada.getSaldo()) + "\nCom limite disponível de R$"
					+ df.format(contaEncontrada.getLimite()) + "\nTotal(Saldo+Limite) R$"
					+ df.format(contaEncontrada.getLimite() + contaEncontrada.getSaldo());
		} else {
			throw new ContaNotFoundException("Conta não encontrada.");
		}

	}

}
