package br.com.fiap.triaurban.mapper;

import br.com.fiap.triaurban.dto.EstoqueItemDTO;
import br.com.fiap.triaurban.model.EstoqueItem;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface EstoqueItemMapper {
    EstoqueItemDTO toDto(EstoqueItem estoqueItem);
    EstoqueItem toEntity(EstoqueItemDTO estoqueItemDTO);
}
