package com.github.joaopmerlin.rinhadebackend.http.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.joaopmerlin.rinhadebackend.entity.TipoTransacao;

import java.time.OffsetDateTime;

public record ExtratoTransacoesResponse(Integer valor,
                                        TipoTransacao tipo,
                                        String descricao,
                                        @JsonProperty("realizada_em") OffsetDateTime realizadaEm) {
}
