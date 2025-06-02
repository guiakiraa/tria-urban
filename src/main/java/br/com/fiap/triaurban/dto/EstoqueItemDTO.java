package br.com.fiap.triaurban.dto;

import java.time.LocalDateTime;

import lombok.Builder;

@Builder
public record EstoqueItemDTO(
        Long id,
        int quantidade,
        LocalDateTime dataAtualizacao,
        ItemDTO item,
        PontoDistribuicaoDTO pontoDistribuicao
) {}