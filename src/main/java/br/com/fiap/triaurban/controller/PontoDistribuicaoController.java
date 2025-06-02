package br.com.fiap.triaurban.controller;

import br.com.fiap.triaurban.dto.PontoDistribuicaoDTO;
import br.com.fiap.triaurban.model.PontoDistribuicao;
import br.com.fiap.triaurban.projection.PontoComTotalProjection;
import br.com.fiap.triaurban.repository.PontoDistribuicaoRepository;
import br.com.fiap.triaurban.service.caching.PontoDistribuicaoCachingService;
import br.com.fiap.triaurban.service.paginacao.PontoDistribuicaoPaginacaoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/pontos_distribuicao")
public class PontoDistribuicaoController {
    @Autowired
    private PontoDistribuicaoRepository repository;

    @Autowired
    private PontoDistribuicaoCachingService cachingService;

    @Autowired
    private PontoDistribuicaoPaginacaoService paginacaoService;

    @Operation(
            tags = "Retorno de informação",
            summary = "Busca todos os pontos de distribuição"
    )
    @GetMapping(value = "/listar")
    public List<PontoDistribuicaoDTO> listar() {
        return cachingService.findAll();
    }

    @Operation(
            tags = "Retorno de informação",
            summary = "Busca todos os pontos de distribuição e exibe em forma de páginas. Evitando muitos resultados de um vez"
    )
    @GetMapping("/paginadas")
    public ResponseEntity<Page<PontoDistribuicaoDTO>> paginar(
            @RequestParam(value = "pagina", defaultValue = "0") Integer page,
            @RequestParam(value = "tamanho", defaultValue = "2") Integer size
    ) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<PontoDistribuicaoDTO> pontosPaginados = paginacaoService.paginar(pageRequest);
        return new ResponseEntity<>(pontosPaginados, HttpStatus.OK);
    }

    @Operation(
            tags = "Retorno de informação",
            summary = "Busca um ponto de distribuição pelo ID"
    )
    @GetMapping(value = "/{id}")
    public PontoDistribuicaoDTO buscarPorId(@PathVariable Long id) {
        Optional<PontoDistribuicaoDTO> pontoDistribuicao = cachingService.findById(id);
        if (pontoDistribuicao.isPresent()) {
            return pontoDistribuicao.get();
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(
            tags = "Inserção de informação",
            summary = "Cadastra um ponto de distribuição"
    )
    @PostMapping("/inserir")
    public PontoDistribuicao salvar(@RequestBody PontoDistribuicao pontoDistribuicao) {
        repository.save(pontoDistribuicao);
        cachingService.clearCache();
        return pontoDistribuicao;
    }

    @Operation(
            tags = "Atualização de informação",
            summary = "Atualiza um ponto de distribuição já existente pelo ID"
    )
    @PutMapping(value = "/atualizar/{id}")
    public PontoDistribuicao atualizar(@PathVariable Long id, @RequestBody PontoDistribuicao filial) {
        Optional<PontoDistribuicao> pontoDistribuicaoOptional = repository.findById(id);
        if (pontoDistribuicaoOptional.isPresent()) {
            PontoDistribuicao pontoDistribuicaoAtual = pontoDistribuicaoOptional.get();
            pontoDistribuicaoAtual.setNome(filial.getNome());
            pontoDistribuicaoAtual.setTipo(filial.getTipo());
            repository.save(pontoDistribuicaoAtual);
            cachingService.clearCache();
            return pontoDistribuicaoAtual;
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(
            tags = "Remoção de informação",
            summary = "Remove um ponto de distribuição pelo ID"
    )
    @DeleteMapping("/remover/{id}")
    public PontoDistribuicao remover(@PathVariable Long id) {
        Optional<PontoDistribuicao> pontoDistribuicao = repository.findById(id);
        if (pontoDistribuicao.isPresent()) {
            repository.delete(pontoDistribuicao.get());
            cachingService.clearCache();
            return pontoDistribuicao.get();
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(
            tags = "Retorno de informação",
            summary = "Busca o total de itens de cada ponto ordenado pelo quantidade de itens"
    )
    @GetMapping(value = "buscarTotalItensPontos")
    public List<PontoComTotalProjection> buscarTotalItensPontos() {
        return cachingService.buscarTotalItensDosPontos();
    }
}
