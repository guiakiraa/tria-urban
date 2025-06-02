package br.com.fiap.triaurban.mapper;

import br.com.fiap.triaurban.dto.PontoDistribuicaoDTO;
import br.com.fiap.triaurban.model.PontoDistribuicao;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface PontoDistribuicaoMapper {
    PontoDistribuicaoDTO toDto(PontoDistribuicao pontoDistribuicao);
    PontoDistribuicao toEntity(PontoDistribuicaoDTO pontoDistribuicaoDTO);
}
