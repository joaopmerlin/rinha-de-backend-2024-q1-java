package com.github.joaopmerlin.rinhadebackend.repository;

import com.github.joaopmerlin.rinhadebackend.entity.Transacao;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransacaoRepository extends CrudRepository<Transacao, Integer> {

    @Query("select * from transacao where cliente_id = :clienteId order by realizada_em desc limit 10")
    List<Transacao> findByClienteId(Integer clienteId);
}
