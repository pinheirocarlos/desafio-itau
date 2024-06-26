package com.desafioitau.api.transferencia.infrastructure.feign;

import com.desafioitau.api.transferencia.domain.Notificacao;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "bacen-api", url = "${app.feign.bacen.host}")
public interface BacenApiClient {
    @PostMapping("/notificacoes")
    void notificaBacen(@RequestBody Notificacao notificacaoRequestDTO);
}
