package com.desafioitau.api.transferencia.infrastructure.feign;

import com.desafioitau.api.transferencia.application.dto.response.ClienteResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CadastroApiClient", url = "${app.feign.cadastro.host}")
public interface CadastroApiClient {
    @GetMapping("/clientes/{id}")
    ClienteResponseDTO buscaCadastroClientePorId(@PathVariable("id") String id);
}
