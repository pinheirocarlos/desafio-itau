package com.desafioitau.api.transferencia.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Schema(description = "Representação do retorno ao efetuar transferência.")
@Getter
@Setter
public class TransferenciaResponseDTO {

    private UUID idTransferencia;
}
