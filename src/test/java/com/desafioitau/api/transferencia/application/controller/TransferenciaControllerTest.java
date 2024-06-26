package com.desafioitau.api.transferencia.application.controller;

import com.desafioitau.api.transferencia.application.dto.request.TransferenciaRequestDTO;
import com.desafioitau.api.transferencia.application.dto.response.TransferenciaResponseDTO;
import com.desafioitau.api.transferencia.application.service.TransferenciaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(TransferenciaController.class)
public class TransferenciaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransferenciaService transferenciaService;

    private TransferenciaRequestDTO createValidTransferenciaRequest() {
        TransferenciaRequestDTO requestDTO = new TransferenciaRequestDTO();
        requestDTO.setIdCliente("123");
        requestDTO.setValor(100.0);
        TransferenciaRequestDTO.Conta conta = new TransferenciaRequestDTO.Conta();
        conta.setIdOrigem("origem123");
        conta.setIdDestino("destino123");
        requestDTO.setConta(conta);
        return requestDTO;
    }

    @Test
    void deveLancarMethodArgumentNotValidExceptionQuandoDadosInvalidos() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/transferencia")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"idCliente\": \"\", \"valor\": -1, \"conta\": { \"idOrigem\": \"\", \"idDestino\": \"\" } }"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deveEfetuarTransferenciaComSucesso() throws Exception {
        TransferenciaRequestDTO requestDTO = createValidTransferenciaRequest();

        TransferenciaResponseDTO responseDTO = new TransferenciaResponseDTO();
        responseDTO.setIdTransferencia(UUID.randomUUID());

        Mockito.when(transferenciaService.efetuarTransferencia(any(TransferenciaRequestDTO.class)))
                .thenReturn(responseDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/transferencia")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"idCliente\": \"123\", \"valor\": 100.0, \"conta\": { \"idOrigem\": \"origem123\", \"idDestino\": \"destino123\" } }"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{ \"idTransferencia\": \"" + responseDTO.getIdTransferencia() + "\" }"));
    }
}

