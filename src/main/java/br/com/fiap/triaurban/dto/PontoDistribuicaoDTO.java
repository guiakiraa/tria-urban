package br.com.fiap.triaurban.dto;

import br.com.fiap.triaurban.model.TipoPontoEnum;
import lombok.Builder;

@Builder
public record PontoDistribuicaoDTO(
        Long id,
        TipoPontoEnum tipo,
        String nome,
        UsuarioDTO usuario,
        EnderecoDTO endereco
) {}