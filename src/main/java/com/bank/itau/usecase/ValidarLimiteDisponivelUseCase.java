package com.bank.itau.usecase;

import com.bank.itau.controller.dto.TransacaoRequest;

public interface ValidarLimiteDisponivelUseCase {

	void validarLimiteDisponivel(TransacaoRequest transacao);

}
