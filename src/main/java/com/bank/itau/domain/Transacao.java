package com.bank.itau.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "tb_transacoes")
public class Transacao {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer agenciaOrigem;
    private Integer contaOrigem;
    private Integer agenciaDestino;
    private Integer contaDestino;
    private double valor;
    private LocalDateTime data;
}
