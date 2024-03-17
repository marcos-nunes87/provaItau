package com.bank.itau.usecase;

import com.bank.itau.controller.dto.TransacaoRequest;

public interface EfetuarTransferenciaUseCase {

	void efetuarTransferencia(TransacaoRequest transacao);

}
