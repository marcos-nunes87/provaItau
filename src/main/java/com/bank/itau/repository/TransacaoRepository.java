package com.bank.itau.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bank.itau.domain.Transacao;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Integer> {
    @Query("SELECT SUM(t.valor) FROM Transacao t WHERE YEAR(t.data) = YEAR(:data) AND MONTH(t.data) = MONTH(:data) AND DAY(t.data) = DAY(:data)")
    Optional<Double> sumValorByData(LocalDate data);
}