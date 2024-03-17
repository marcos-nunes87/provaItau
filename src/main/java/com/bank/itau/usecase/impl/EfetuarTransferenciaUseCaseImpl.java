package com.bank.itau.usecase.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.bank.itau.controller.dto.TransacaoRequest;
import com.bank.itau.domain.Conta;
import com.bank.itau.domain.ContaId;
import com.bank.itau.domain.Transacao;
import com.bank.itau.exception.ContaInativaException;
import com.bank.itau.exception.ContaNotFoundException;
import com.bank.itau.repository.ContaRepository;
import com.bank.itau.repository.TransacaoRepository;
import com.bank.itau.usecase.EfetuarTransferenciaUseCase;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EfetuarTransferenciaUseCaseImpl implements EfetuarTransferenciaUseCase {

	private final ContaRepository contaRepository;
    private final TransacaoRepository transacaoRepository;

    public EfetuarTransferenciaUseCaseImpl(ContaRepository contaRepository, TransacaoRepository transacaoRepository) {
        this.contaRepository = contaRepository;
        this.transacaoRepository = transacaoRepository;
    }

    @Override
	public void efetuarTransferencia(TransacaoRequest transacao) {
    	log.info("efetuarTransferencia - " + transacao);
        Conta origem = contaRepository.findByContaId(new ContaId(transacao.getAgenciaOrigem(), transacao.getContaOrigem()))
                .orElseThrow(() -> new ContaNotFoundException("Conta de origem não encontrada"));

        Conta destino = contaRepository.findByContaId(new ContaId(transacao.getAgenciaDestino(), transacao.getContaDestino()))
                .orElseThrow(() -> new ContaNotFoundException("Conta de destino não encontrada"));

        if (!origem.isAtivo() || !destino.isAtivo()) {
            throw new ContaInativaException("Uma ou ambas as contas estão inativas");
        }

    	log.info("efetuarTransferencia - atualizando saldos");
        origem.debitar(transacao.getValor());
        destino.creditar(transacao.getValor());
        contaRepository.save(origem);
        contaRepository.save(destino);

    	log.info("efetuarTransferencia - efetuando transferência");
        Transacao novaTransacao = new Transacao();
        novaTransacao.setAgenciaOrigem(transacao.getAgenciaOrigem());
        novaTransacao.setContaOrigem(transacao.getContaOrigem());
        novaTransacao.setAgenciaDestino(transacao.getAgenciaDestino());
        novaTransacao.setContaDestino(transacao.getContaDestino());
        novaTransacao.setValor(transacao.getValor());
        novaTransacao.setData(LocalDateTime.now());
        transacaoRepository.save(novaTransacao);
	}

}
