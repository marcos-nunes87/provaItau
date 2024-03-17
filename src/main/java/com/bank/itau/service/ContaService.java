package com.bank.itau.service;

import org.springframework.stereotype.Service;

import com.bank.itau.controller.dto.TransacaoRequest;
import com.bank.itau.usecase.ConsultarSaldoContaUseCase;
import com.bank.itau.usecase.EfetuarTransferenciaUseCase;
import com.bank.itau.usecase.NotificarBacenUseCase;
import com.bank.itau.usecase.ValidarContaAtivaUseCase;
import com.bank.itau.usecase.ValidarLimiteDiarioUseCase;
import com.bank.itau.usecase.ValidarLimiteDisponivelUseCase;

@Service
public class ContaService {

	private final ConsultarSaldoContaUseCase consultarSaldoContaUseCase;
	private final ValidarContaAtivaUseCase validarContaAtivaUseCase;
    private final ValidarLimiteDisponivelUseCase validarLimiteDisponivelUseCase;
    private final ValidarLimiteDiarioUseCase validarLimiteDiarioUseCase;
    private final EfetuarTransferenciaUseCase efetuarTransferenciaUseCase;
    private final NotificarBacenUseCase notificarBacenUseCase;

    public ContaService(ConsultarSaldoContaUseCase consultarSaldoContaUseCase,
    						ValidarContaAtivaUseCase validarContaAtivaUseCase,
                            ValidarLimiteDisponivelUseCase validarLimiteDisponivelUseCase,
                            ValidarLimiteDiarioUseCase validarLimiteDiarioUseCase,
                            EfetuarTransferenciaUseCase efetuarTransferenciaUseCase,
                            NotificarBacenUseCase notificarBacenUseCase) {
    	this.consultarSaldoContaUseCase = consultarSaldoContaUseCase;
        this.validarContaAtivaUseCase = validarContaAtivaUseCase;
        this.validarLimiteDisponivelUseCase = validarLimiteDisponivelUseCase;
        this.validarLimiteDiarioUseCase = validarLimiteDiarioUseCase;
        this.efetuarTransferenciaUseCase = efetuarTransferenciaUseCase;
        this.notificarBacenUseCase = notificarBacenUseCase;
    }

	public String consultarSaldoConta(Integer agencia, Integer conta) {
		String saldo = consultarSaldoContaUseCase.consultarSaldo(agencia,conta);

		return saldo;
	}

	public void efetuarTransferencia(TransacaoRequest transacao) {
        validarContaAtivaUseCase.validarContaAtiva(transacao.getAgenciaOrigem(),transacao.getContaOrigem());
        validarContaAtivaUseCase.validarContaAtiva(transacao.getAgenciaDestino(),transacao.getContaDestino());
        validarLimiteDisponivelUseCase.validarLimiteDisponivel(transacao);
        validarLimiteDiarioUseCase.validarLimiteDiario(transacao);
        efetuarTransferenciaUseCase.efetuarTransferencia(transacao);
        notificarBacenUseCase.notificarTransacao(transacao);
	}

}
