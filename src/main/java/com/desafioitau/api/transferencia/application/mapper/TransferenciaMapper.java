package com.desafioitau.api.transferencia.application.mapper;

import com.desafioitau.api.transferencia.application.dto.request.SaldoRequestDTO;
import com.desafioitau.api.transferencia.application.dto.request.TransferenciaRequestDTO;
import com.desafioitau.api.transferencia.domain.Notificacao;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface TransferenciaMapper {
    Notificacao toNotificacaoModel(TransferenciaRequestDTO transferenciaRequestDTO);

    SaldoRequestDTO toSaldoRequestDto(TransferenciaRequestDTO transferenciaRequestDTO);
}