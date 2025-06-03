package br.com.fiap.triaurban.dto;

import lombok.Builder;

@Builder
public record EstoqueItemDTO(
        int quantidade,
        ItemDTO item,
        PontoDistribuicaoDTO pontoDistribuicao
) {}