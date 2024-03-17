package com.bank.itau.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class TransacaoRequest {
	private Integer agenciaOrigem;
    private Integer contaOrigem;
    private Integer agenciaDestino;
    private Integer contaDestino;
    private double valor;
}
