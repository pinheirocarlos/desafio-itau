package com.desafioitau.api.transferencia.application.service.integration;

import com.desafioitau.api.transferencia.application.dto.request.SaldoRequestDTO;
import com.desafioitau.api.transferencia.application.dto.response.ClienteResponseDTO;
import com.desafioitau.api.transferencia.application.dto.response.ContaResponseDTO;
import com.desafioitau.api.transferencia.application.exception.ErroPontualException;
import com.desafioitau.api.transferencia.domain.Notificacao;
import com.desafioitau.api.transferencia.infrastructure.feign.BacenApiClient;
import com.desafioitau.api.transferencia.infrastructure.feign.CadastroApiClient;
import com.desafioitau.api.transferencia.infrastructure.feign.ContaApiClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AppIntegration {
    private static final Logger logger = LoggerFactory.getLogger(AppIntegration.class);
    private final BacenApiClient bacenApiClient;
    private final CadastroApiClient cadastroApiClient;
    private final ContaApiClient contaApiClient;

    public AppIntegration(BacenApiClient bacenApiClient, CadastroApiClient cadastroApiClient, ContaApiClient contaApiClient) {
        this.bacenApiClient = bacenApiClient;
        this.cadastroApiClient = cadastroApiClient;
        this.contaApiClient = contaApiClient;
    }

    @Retryable(retryFor = {ErroPontualException.class}, backoff = @Backoff(delay = 3000))
    public void notificaBacen(Notificacao notificacao) {
        logger.info("notificaBacen -> " + LocalDateTime.now());
        bacenApiClient.notificaBacen(notificacao);
    }

    @Retryable(retryFor = {ErroPontualException.class}, backoff = @Backoff(delay = 3000))
    public ClienteResponseDTO buscaCadastroClientePorId(String idCliente) {
        logger.info("buscaCadastroClientePorId -> " + LocalDateTime.now());
        return cadastroApiClient.buscaCadastroClientePorId(idCliente);
    }

    @Retryable(retryFor = {ErroPontualException.class}, backoff = @Backoff(delay = 3000))
    public ContaResponseDTO buscaContasPorId(String idConta) {
        logger.info("buscaContasPorId -> " + LocalDateTime.now());
        return contaApiClient.buscaContasPorId(idConta);
    }

    @Retryable(retryFor = {ErroPontualException.class}, backoff = @Backoff(delay = 3000))
    public ContaResponseDTO atualizaSaldoDaConta(SaldoRequestDTO saldoRequestDTO) {
        logger.info("buscaContasPorId -> " + LocalDateTime.now());
        return contaApiClient.atualizaSaldoDaConta(saldoRequestDTO);
    }


}
