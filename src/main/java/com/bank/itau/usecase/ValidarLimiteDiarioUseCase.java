package com.bank.itau.usecase;

import com.bank.itau.controller.dto.TransacaoRequest;

public interface ValidarLimiteDiarioUseCase {

	void validarLimiteDiario(TransacaoRequest transacao);

}
