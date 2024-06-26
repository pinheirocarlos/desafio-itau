package com.desafioitau.api.transferencia.application.service;

import com.desafioitau.api.transferencia.application.dto.request.TransferenciaRequestDTO;
import com.desafioitau.api.transferencia.application.dto.response.ClienteResponseDTO;
import com.desafioitau.api.transferencia.application.dto.response.ContaResponseDTO;
import com.desafioitau.api.transferencia.application.dto.response.TransferenciaResponseDTO;
import com.desafioitau.api.transferencia.application.exception.RegraDeNegocioException;
import com.desafioitau.api.transferencia.application.mapper.TransferenciaMapper;
import com.desafioitau.api.transferencia.application.service.integration.AppIntegration;
import com.desafioitau.api.transferencia.domain.Notificacao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransferenciaServiceTest {

    @Mock
    private AppIntegration appIntegration;

    @Spy
    private TransferenciaMapper transferenciaMapper;

    @InjectMocks
    private TransferenciaService transferenciaService;
    private TransferenciaRequestDTO requestDTO;

    @BeforeEach
    void setUp() {
        TransferenciaRequestDTO.Conta conta = new TransferenciaRequestDTO.Conta();
        conta.setIdDestino("41313d7b-bd75-4c75-9dea-1f4be434007f");
        conta.setIdOrigem("d0d32142-74b7-4aca-9c68-838aeacef96b");

        requestDTO = new TransferenciaRequestDTO();
        requestDTO.setIdCliente("bcdd1048-a501-4608-bc82-66d7b4db3600");
        requestDTO.setConta(conta);
        requestDTO.setValor(500);
    }

    @Test
    void deveLancarExcecaoQuandoClienteNaoEncontrado() {
        when(appIntegration.buscaCadastroClientePorId(any())).thenReturn(null);

        RegraDeNegocioException exception = assertThrows(RegraDeNegocioException.class, () ->
                transferenciaService.efetuarTransferencia(requestDTO));

        assertEquals("Não é possível realizar a transferência devido a um problema no idConta", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoContaInativa() {
        mockClienteAndConta(false, 0.0, 0.0);

        RegraDeNegocioException exception = assertThrows(RegraDeNegocioException.class, () ->
                transferenciaService.efetuarTransferencia(requestDTO));

        assertEquals("Não é possível realizar a transferência, a conta está inativa", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoSaldoInsuficiente() {
        mockClienteAndConta(true, 50.0, 50.0);

        requestDTO.setValor(100.0);

        RegraDeNegocioException exception = assertThrows(RegraDeNegocioException.class, () ->
                transferenciaService.efetuarTransferencia(requestDTO));

        assertEquals("Não é possível realizar a transferência, verifique seu saldo", exception.getMessage());
    }

    @Test
    void deveEfetuarTransferenciaComSucesso() {
        mockClienteAndConta(true, 500.0, 500.0);

        Notificacao notificacao = new Notificacao();
        when(transferenciaMapper.toNotificacaoModel(requestDTO)).thenReturn(notificacao);

        doNothing().when(appIntegration).notificaBacen(notificacao);

        TransferenciaResponseDTO responseDTO = transferenciaService.efetuarTransferencia(requestDTO);

        assertNotNull(responseDTO);
        assertNotNull(responseDTO.getIdTransferencia());
    }

    private void mockClienteAndConta(boolean contaAtiva, double saldo, double limiteDiario) {
        ClienteResponseDTO clienteResponseDTO = new ClienteResponseDTO();
        when(appIntegration.buscaCadastroClientePorId(any())).thenReturn(clienteResponseDTO);

        ContaResponseDTO contaResponseDTO = new ContaResponseDTO();
        contaResponseDTO.setAtivo(contaAtiva);
        contaResponseDTO.setSaldo(saldo);
        contaResponseDTO.setLimiteDiario(limiteDiario);
        when(appIntegration.buscaContasPorId(any())).thenReturn(contaResponseDTO);
    }
}
