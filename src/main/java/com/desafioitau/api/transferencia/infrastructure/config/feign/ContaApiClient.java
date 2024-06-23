package com.desafioitau.api.transferencia.infrastructure.config.feign;

import com.desafioitau.api.transferencia.dto.request.SaldoRequestDTO;
import com.desafioitau.api.transferencia.dto.response.ContaResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "ContaApiClient", url = "${app.feign.conta.host}")
public interface ContaApiClient {
    @GetMapping("/contas/{id}")
    ContaResponseDTO buscaContasPorId(@RequestParam("id") String id);

    @PutMapping("/contas/saldos")
    ContaResponseDTO atualizaSaldoDaConta(@RequestBody SaldoRequestDTO saldoRequestDTO);
}
