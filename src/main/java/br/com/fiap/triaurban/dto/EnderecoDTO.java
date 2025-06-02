package br.com.fiap.triaurban.dto;

import br.com.fiap.triaurban.model.UfEnum;
import lombok.Builder;

@Builder
public record EnderecoDTO(
        Long id,
        String logradouro,
        String numero,
        String bairro,
        String cidade,
        UfEnum uf,
        String cep,
        String complemento
) {}