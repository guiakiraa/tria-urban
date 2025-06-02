package br.com.fiap.triaurban.dto;

import lombok.Builder;

@Builder
public record UsuarioDTO(
        Long id,
        String nomeCompleto,
        String username,
        String email,
        String senha,
        EnderecoDTO endereco
) {}