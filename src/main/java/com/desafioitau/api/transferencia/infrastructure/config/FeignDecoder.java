package com.desafioitau.api.transferencia.infrastructure.config;

import com.desafioitau.api.transferencia.application.exception.ErroPontualException;
import com.desafioitau.api.transferencia.application.exception.NotFoundException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;

public class FeignDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response != null) {
            int status = response.status();
            if (status == HttpStatus.TOO_MANY_REQUESTS.value() ||
                    status == HttpStatus.REQUEST_TIMEOUT.value()) {
                throw new ErroPontualException(response.reason());
            } else if (status == HttpStatus.NOT_FOUND.value()) {
                throw new NotFoundException(response.reason());
            } else {
                return defaultErrorDecoder.decode(methodKey, response);
            }
        }
        return new Exception("Ocorreu um erro desconhecido na integração.");
    }
}
