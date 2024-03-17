package com.bank.itau.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bank.itau.domain.Conta;
import com.bank.itau.domain.ContaId;

@Repository
public interface ContaRepository extends JpaRepository<Conta,ContaId>{
	@Query("SELECT c FROM Conta c WHERE c.id = :contaId")
    Optional<Conta> findByContaId(ContaId contaId);
}
