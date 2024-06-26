package com.desafioitau.api.transferencia.application.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaldoRequestDTO {

    private double valor;

    @Getter
    @Setter
    public static class Conta {
        private String idOrigem;
        private String idDestino;
    }
}
