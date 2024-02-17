package com.github.joaopmerlin.rinhadebackend.http.dto;

public record RegistraTransacaoRequest(String valor,
                                       String tipo,
                                       String descricao) {

    public boolean valida() {
        return validaValor() &&
                tipo != null &&
                (tipo.equals("d") || tipo.equals("c")) &&
                descricao != null &&
                !descricao.isBlank() &&
                descricao.length() <= 10;
    }

    private boolean validaValor() {
        if (valor == null || valor.isEmpty()) {
            return false;
        }
        try {
            int valorInt = Integer.parseInt(valor);
            return valorInt > 0;
        } catch (Exception e) {
            return false;
        }
    }
}
