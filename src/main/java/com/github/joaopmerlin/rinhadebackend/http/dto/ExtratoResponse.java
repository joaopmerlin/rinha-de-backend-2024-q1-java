package com.github.joaopmerlin.rinhadebackend.http.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record ExtratoResponse(ExtratoSaldoResponse saldo,
                              @JsonProperty("ultimas_transacoes") List<ExtratoTransacoesResponse> ultimasTransacoes) {
}
