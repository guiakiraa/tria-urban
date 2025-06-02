package br.com.fiap.triaurban.service.caching;

import br.com.fiap.triaurban.dto.PontoDistribuicaoDTO;
import br.com.fiap.triaurban.mapper.PontoDistribuicaoMapper;
import br.com.fiap.triaurban.model.PontoDistribuicao;
import br.com.fiap.triaurban.projection.PontoComTotalProjection;
import br.com.fiap.triaurban.repository.PontoDistribuicaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PontoDistribuicaoCachingService {
    @Autowired
    private PontoDistribuicaoRepository pontoDistribuicaoRepository;

    @Autowired
    private PontoDistribuicaoMapper pontoDistribuicaoMapper;

    private PontoDistribuicaoDTO mapearParaDTO(PontoDistribuicao pontoDistribuicao) {
        return pontoDistribuicaoMapper.toDto(pontoDistribuicao);
    }

    @Cacheable(value = "listarPontosDistribuicao")
    public List<PontoDistribuicaoDTO> findAll() {
        List<PontoDistribuicao> pontosDistribuicao = pontoDistribuicaoRepository.findAll();
        return pontosDistribuicao.stream().map(this::mapearParaDTO).toList();
    }

    @Cacheable(value = "buscarPontoDistribuicaoPorId", key = "#id")
    public Optional<PontoDistribuicaoDTO> findById(Long id) {
        return pontoDistribuicaoRepository.findById(id)
                .map(this::mapearParaDTO);
    }

    @Cacheable(value = "paginarPontosDistribuicao", key = "#pageRequest")
    public Page<PontoDistribuicaoDTO> paginar(PageRequest pageRequest) {
        Page<PontoDistribuicao> pontosDistribuicao = pontoDistribuicaoRepository.findAll(pageRequest);
        return pontosDistribuicao.map(this::mapearParaDTO);
    }

    @Cacheable(value = "buscarTotalItensDosPontos")
    public List<PontoComTotalProjection> buscarTotalItensDosPontos() {
        return pontoDistribuicaoRepository.encontrarPontosComMaisItens();
    }

    @CacheEvict(value = {"listarPontosDistribuicao", "paginarPontosDistribuicao", "buscarPontoDistribuicaoPorId", "buscarTotalItensDosPontos"}, allEntries = true)
    public void clearCache() {
    }
}
