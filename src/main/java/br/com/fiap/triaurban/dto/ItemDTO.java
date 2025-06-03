package br.com.fiap.triaurban.dto;

import br.com.fiap.triaurban.model.CategoriaItemEnum;

import lombok.Builder;

@Builder
public record ItemDTO(
        String nome,
        CategoriaItemEnum categoria
) {}