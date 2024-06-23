package com.desafioitau.api.transferencia.service;

import com.desafioitau.api.transferencia.dto.request.TransferenciaRequestDTO;
import com.desafioitau.api.transferencia.dto.response.ClienteResponseDTO;
import com.desafioitau.api.transferencia.dto.response.ContaResponseDTO;
import com.desafioitau.api.transferencia.dto.response.TransferenciaResponseDTO;
import com.desafioitau.api.transferencia.infrastructure.config.feign.BacenApiClient;
import com.desafioitau.api.transferencia.infrastructure.config.feign.CadastroApiClient;
import com.desafioitau.api.transferencia.infrastructure.config.feign.ContaApiClient;
import org.springframework.stereotype.Service;

@Service
public class TransferenciaService {
    private final BacenApiClient bacenApiClient;
    private final CadastroApiClient cadastroApiClient;
    private final ContaApiClient contaApiClient;


    public TransferenciaService(BacenApiClient bacenApiClient, CadastroApiClient cadastroApiClient, ContaApiClient contaApiClient) {
        this.bacenApiClient = bacenApiClient;
        this.cadastroApiClient = cadastroApiClient;
        this.contaApiClient = contaApiClient;
    }

    public TransferenciaResponseDTO efetuarTransferencia(TransferenciaRequestDTO transferenciaRequest) {
        ClienteResponseDTO dadosCliente = cadastroApiClient.buscaCadastroClientePorId(transferenciaRequest.getIdCliente());
        ContaResponseDTO dadosConta = contaApiClient.buscaContasPorId(transferenciaRequest.getConta().getIdOrigem());
        return new TransferenciaResponseDTO();
    }
}
