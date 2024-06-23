package com.desafioitau.api.transferencia.infrastructure.config.feign;

import com.desafioitau.api.transferencia.dto.response.ClienteResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "CadastroApiClient", url = "${app.feign.cadastro.host}")
public interface CadastroApiClient {
    @GetMapping("/clientes/{id}")
    ClienteResponseDTO buscaCadastroClientePorId(@RequestParam("id") String id);
}
