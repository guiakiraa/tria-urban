package br.com.fiap.triaurban.mapper;

import br.com.fiap.triaurban.dto.EnderecoDTO;
import br.com.fiap.triaurban.model.Endereco;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface EnderecoMapper {
    EnderecoDTO toDto(Endereco endereco);
    Endereco toEntity(EnderecoDTO enderecoDto);
}
