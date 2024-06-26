package com.desafioitau.api.transferencia.application.service;

import com.desafioitau.api.transferencia.application.dto.request.SaldoRequestDTO;
import com.desafioitau.api.transferencia.application.dto.request.TransferenciaRequestDTO;
import com.desafioitau.api.transferencia.application.dto.response.ClienteResponseDTO;
import com.desafioitau.api.transferencia.application.dto.response.ContaResponseDTO;
import com.desafioitau.api.transferencia.application.dto.response.TransferenciaResponseDTO;
import com.desafioitau.api.transferencia.application.exception.RegraDeNegocioException;
import com.desafioitau.api.transferencia.application.mapper.TransferenciaMapper;
import com.desafioitau.api.transferencia.application.service.integration.AppIntegration;
import com.desafioitau.api.transferencia.domain.Notificacao;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TransferenciaService {
    private static final double SALDO_ZERADO = 0.0;
    private final AppIntegration appIntegration;
    private final TransferenciaMapper transferenciaMapper;

    public TransferenciaService(AppIntegration appIntegration, TransferenciaMapper transferenciaMapper) {
        this.appIntegration = appIntegration;
        this.transferenciaMapper = transferenciaMapper;
    }

    public TransferenciaResponseDTO efetuarTransferencia(TransferenciaRequestDTO transferenciaRequest) {
        validarCliente(transferenciaRequest.getIdCliente());
        validarConta(transferenciaRequest.getConta().getIdOrigem(), transferenciaRequest.getValor());

        SaldoRequestDTO saldoRequestDTO = transferenciaMapper.toSaldoRequestDto(transferenciaRequest);
        appIntegration.atualizaSaldoDaConta(saldoRequestDTO);

        Notificacao notificacao = transferenciaMapper.toNotificacaoModel(transferenciaRequest);
        appIntegration.notificaBacen(notificacao);

        TransferenciaResponseDTO transferenciaResponseDTO = new TransferenciaResponseDTO();
        transferenciaResponseDTO.setIdTransferencia(UUID.randomUUID());
        return transferenciaResponseDTO;
    }

    private void validarCliente(String idCliente) {
        ClienteResponseDTO clienteResponseDTO = appIntegration.buscaCadastroClientePorId(idCliente);
        if (clienteResponseDTO == null) {
            throw new RegraDeNegocioException("Não é possível realizar a transferência devido a um problema no idConta");
        }
    }

    private void validarConta(String idConta, double valorTransferencia) {
        ContaResponseDTO contaResponseDTO = appIntegration.buscaContasPorId(idConta);
        if (!contaResponseDTO.isAtivo()) {
            throw new RegraDeNegocioException("Não é possível realizar a transferência, a conta está inativa");
        }
        if (contaResponseDTO.getSaldo() == SALDO_ZERADO
                || contaResponseDTO.getLimiteDiario() < valorTransferencia) {
            throw new RegraDeNegocioException("Não é possível realizar a transferência, verifique seu saldo");
        }
    }
}
