package com.desafioitau.api.transferencia.infrastructure.config.feign;

import com.desafioitau.api.transferencia.dto.request.NotificacaoRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "BacenApiClient", url = "${app.feign.bacen.host}")
public interface BacenApiClient {
    @PostMapping("/notificacoes")
    void notificaBacen(@RequestBody NotificacaoRequestDTO notificacaoRequestDTO);
}
