package com.github.joaopmerlin.rinhadebackend.entity;

import org.springframework.data.annotation.Id;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

public final class Cliente {

    @Id
    private final Integer id;
    private final Integer limite;
    private Integer saldo;

    public Cliente(Integer id,
                   Integer limite,
                   Integer saldo) {
        this.id = id;
        this.limite = limite;
        this.saldo = saldo;
    }

    public void atualizaSaldo(Integer valor, TipoTransacao tipoTransacao) {
        if (tipoTransacao.equals(TipoTransacao.c)) {
            saldo += valor;
        } else {
            saldo -= valor;
        }
    }

    public boolean temLimite(Integer valor, TipoTransacao tipoTransacao) {
        return !tipoTransacao.equals(TipoTransacao.d) || (saldo + limite) >= valor;
    }

    @Id
    public Integer id() {
        return id;
    }

    public Integer limite() {
        return limite;
    }

    public Integer saldo() {
        return saldo;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Cliente) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.limite, that.limite) &&
                Objects.equals(this.saldo, that.saldo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, limite, saldo);
    }

    @Override
    public String toString() {
        return "Cliente[" +
                "id=" + id + ", " +
                "limite=" + limite + ", " +
                "saldo=" + saldo + ']';
    }

}