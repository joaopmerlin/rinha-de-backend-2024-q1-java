package com.github.joaopmerlin.rinhadebackend.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import java.time.OffsetDateTime;

public record Transacao(@Id Integer id,
                        @Column("cliente_id") Integer clienteId,
                        Integer valor,
                        TipoTransacao tipo,
                        String descricao,
                        @Column("realizada_em") OffsetDateTime realizadaEm) {
}