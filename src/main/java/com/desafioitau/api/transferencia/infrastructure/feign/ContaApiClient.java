package com.desafioitau.api.transferencia.infrastructure.feign;

import com.desafioitau.api.transferencia.application.dto.request.SaldoRequestDTO;
import com.desafioitau.api.transferencia.application.dto.response.ContaResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ContaApiClient", url = "${app.feign.contas.host}")
public interface ContaApiClient {
    @GetMapping("/contas/{id}")
    ContaResponseDTO buscaContasPorId(@PathVariable("id") String id);

    @PutMapping("/contas/saldos")
    ContaResponseDTO atualizaSaldoDaConta(@RequestBody SaldoRequestDTO saldoRequestDTO);
}
