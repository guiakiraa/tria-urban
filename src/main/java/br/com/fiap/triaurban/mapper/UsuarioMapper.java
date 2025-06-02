package br.com.fiap.triaurban.mapper;

import br.com.fiap.triaurban.dto.UsuarioDTO;
import br.com.fiap.triaurban.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UsuarioMapper {
    UsuarioDTO toDto(Usuario usuario);
    Usuario toEntity(UsuarioDTO usuarioDTO);
}
