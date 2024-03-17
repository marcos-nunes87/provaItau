package com.bank.itau.usecase;

import com.bank.itau.controller.dto.TransacaoRequest;

public interface NotificarBacenUseCase {

	void notificarTransacao(TransacaoRequest transacao);

}
