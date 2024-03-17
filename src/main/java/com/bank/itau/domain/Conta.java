package com.bank.itau.domain;

import com.bank.itau.exception.SaldoInsuficienteException;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "tb_contas")
public class Conta {

	@EmbeddedId
	private ContaId id;

	@ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    private double saldo;
    private double limite;
    private boolean ativo;

    public void debitar(double valor) {
        if (this.saldo + this.limite >= valor) {
            this.saldo -= valor;
        } else {
            throw new SaldoInsuficienteException("Saldo insuficiente para debitar o valor");
        }
    }

    public void creditar(double valor) {
        this.saldo += valor;
    }
}
