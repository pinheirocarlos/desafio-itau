package com.desafioitau.api.transferencia.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Notificacao {

    private double valor;

    private Conta conta;

    @Getter
    @Setter
    public static class Conta {
        private String idOrigem;

        private String idDestino;
    }
}
