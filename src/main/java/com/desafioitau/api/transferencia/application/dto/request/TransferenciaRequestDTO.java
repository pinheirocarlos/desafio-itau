package com.desafioitau.api.transferencia.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "Dados necessários para efetuar a transferência.")
@Getter
@Setter
public class TransferenciaRequestDTO {

    @NotEmpty
    private String idCliente;

    @PositiveOrZero
    private double valor;

    private Conta conta;

    @Getter
    @Setter
    public static class Conta {
        @NotEmpty
        private String idOrigem;
        @NotEmpty
        private String idDestino;
    }
}
