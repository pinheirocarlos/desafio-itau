package com.desafioitau.api.transferencia.controller;

import com.desafioitau.api.transferencia.dto.request.TransferenciaRequestDTO;
import com.desafioitau.api.transferencia.dto.response.TransferenciaResponseDTO;
import com.desafioitau.api.transferencia.service.TransferenciaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransferenciaController {

    private final TransferenciaService transferenciaService;

    public TransferenciaController(TransferenciaService transferenciaService) {
        this.transferenciaService = transferenciaService;
    }

    @Operation(summary = "Realiza transferência bancária entre clientes.")
    @PostMapping("/transferencia")
    public ResponseEntity<TransferenciaResponseDTO> efetuarTransferencia(@RequestBody TransferenciaRequestDTO transferenciaRequestDTO) {
        TransferenciaResponseDTO resultado = transferenciaService.efetuarTransferencia(transferenciaRequestDTO);
        return ResponseEntity.ok().body(resultado);
    }
}
