package br.com.fiap.triaurban.dto;

import lombok.Builder;

@Builder
public record UsuarioDTO(
        String nomeCompleto,
        String username,
        String email,
        EnderecoDTO endereco
) {}