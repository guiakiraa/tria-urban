package br.com.fiap.triaurban.controller;

import br.com.fiap.triaurban.dto.EstoqueItemDTO;
import br.com.fiap.triaurban.model.EstoqueItem;
import br.com.fiap.triaurban.repository.EstoqueItemRepository;
import br.com.fiap.triaurban.service.caching.EstoqueItemCachingService;
import br.com.fiap.triaurban.service.paginacao.EstoqueItemPaginacaoService;
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
@RequestMapping(value = "/estoques")
public class EstoqueItemController {
    @Autowired
    private EstoqueItemRepository repository;

    @Autowired
    private EstoqueItemCachingService cachingService;

    @Autowired
    private EstoqueItemPaginacaoService paginacaoService;

    @Operation(
            tags = "Retorno de informação",
            summary = "Busca todos os estoques"
    )
    @GetMapping(value = "/listar")
    public List<EstoqueItemDTO> listar() {
        return cachingService.findAll();
    }

    @Operation(
            tags = "Retorno de informação",
            summary = "Busca todos os estoques e exibe em forma de páginas. Evitando muitos resultados de um vez"
    )
    @GetMapping("/paginadas")
    public ResponseEntity<Page<EstoqueItemDTO>> paginar(
            @RequestParam(value = "pagina", defaultValue = "0") Integer page,
            @RequestParam(value = "tamanho", defaultValue = "2") Integer size
    ) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<EstoqueItemDTO> estoquesPaginadas = paginacaoService.paginar(pageRequest);
        return new ResponseEntity<>(estoquesPaginadas, HttpStatus.OK);
    }

    @Operation(
            tags = "Retorno de informação",
            summary = "Busca um estoque pelo ID"
    )
    @GetMapping(value = "/{id}")
    public EstoqueItemDTO buscarPorId(@PathVariable Long id) {
        Optional<EstoqueItemDTO> estoqueItem = cachingService.findById(id);
        if (estoqueItem.isPresent()) {
            return estoqueItem.get();
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(
            tags = "Inserção de informação",
            summary = "Cadastra um novo estoque"
    )
    @PostMapping("/inserir")
    public EstoqueItem salvar(@RequestBody EstoqueItem estoqueItem) {
        repository.save(estoqueItem);
        cachingService.clearCache();
        return estoqueItem;
    }

    @Operation(
            tags = "Atualização de informação",
            summary = "Atualiza um estoque já existente pelo ID"
    )
    @PutMapping(value = "/atualizar/{id}")
    public EstoqueItem atualizar(@PathVariable Long id, @RequestBody EstoqueItem filial) {
        Optional<EstoqueItem> estoqueItemOptional = repository.findById(id);
        if (estoqueItemOptional.isPresent()) {
            EstoqueItem estoqueItemAtual = estoqueItemOptional.get();
            estoqueItemAtual.setQuantidade(filial.getQuantidade());
            estoqueItemAtual.setDataAtualizacao(filial.getDataAtualizacao());
            repository.save(estoqueItemAtual);
            cachingService.clearCache();
            return estoqueItemAtual;
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(
            tags = "Remoção de informação",
            summary = "Remove um estoque pelo ID"
    )
    @DeleteMapping("/remover/{id}")
    public EstoqueItem remover(@PathVariable Long id) {
        Optional<EstoqueItem> estoqueItem = repository.findById(id);
        if (estoqueItem.isPresent()) {
            repository.delete(estoqueItem.get());
            cachingService.clearCache();
            return estoqueItem.get();
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
