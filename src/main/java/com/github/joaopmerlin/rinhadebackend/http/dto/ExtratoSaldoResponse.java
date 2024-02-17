package com.github.joaopmerlin.rinhadebackend.http.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;

public record ExtratoSaldoResponse(Integer total,
                                   @JsonProperty("data_extrato") OffsetDateTime dataExtrato,
                                   Integer limite) {
}
