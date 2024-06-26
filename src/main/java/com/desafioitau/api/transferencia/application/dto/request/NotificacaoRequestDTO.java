package com.desafioitau.api.transferencia.application.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificacaoRequestDTO {

    private double valor;
    private Conta conta;

    @Getter
    @Setter
    public static class Conta {
        private String idOrigem;
        private String idDestino;
    }
}
