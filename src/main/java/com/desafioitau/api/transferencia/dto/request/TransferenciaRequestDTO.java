package com.desafioitau.api.transferencia.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "Dados necessários para efetuar a transferência.")
@Getter
@Setter
public class TransferenciaRequestDTO {

    private String idCliente;

    private double valor;

    private Conta conta;

    @Getter
    public static class Conta {
        private String idOrigem;

        private String idDestino;
    }
}
