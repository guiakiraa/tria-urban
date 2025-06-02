package br.com.fiap.triaurban.mapper;

import br.com.fiap.triaurban.dto.ItemDTO;
import br.com.fiap.triaurban.model.Item;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ItemMapper {
    ItemDTO toDto(Item item);
    Item toEntity(ItemDTO itemDTO);
}
