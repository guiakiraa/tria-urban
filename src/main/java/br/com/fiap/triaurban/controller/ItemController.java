package br.com.fiap.triaurban.controller;

import br.com.fiap.triaurban.dto.ItemDTO;
import br.com.fiap.triaurban.model.CategoriaItemEnum;
import br.com.fiap.triaurban.model.Item;
import br.com.fiap.triaurban.projection.ItemComEstoqueProjection;
import br.com.fiap.triaurban.projection.ItemPontoProjection;
import br.com.fiap.triaurban.repository.ItemRepository;
import br.com.fiap.triaurban.service.caching.ItemCachingService;
import br.com.fiap.triaurban.service.paginacao.ItemPaginacaoService;
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
@RequestMapping(value = "/itens")
public class ItemController {
    @Autowired
    private ItemRepository repository;

    @Autowired
    private ItemCachingService cachingService;

    @Autowired
    private ItemPaginacaoService paginacaoService;

    @Operation(
            tags = "Retorno de informação",
            summary = "Busca todos os itens"
    )
    @GetMapping(value = "/listar")
    public List<ItemDTO> listar() {
        return cachingService.findAll();
    }

    @Operation(
            tags = "Retorno de informação",
            summary = "Busca todas os itens e exibe em forma de páginas. Evitando muitos resultados de um vez"
    )
    @GetMapping("/paginadas")
    public ResponseEntity<Page<ItemDTO>> paginar(
            @RequestParam(value = "pagina", defaultValue = "0") Integer page,
            @RequestParam(value = "tamanho", defaultValue = "2") Integer size
    ) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<ItemDTO> itensPaginadas = paginacaoService.paginar(pageRequest);
        return new ResponseEntity<>(itensPaginadas, HttpStatus.OK);
    }

    @Operation(
            tags = "Retorno de informação",
            summary = "Busca um item pelo ID"
    )
    @GetMapping(value = "/{id}")
    public ItemDTO buscarPorId(@PathVariable Long id) {
        Optional<ItemDTO> item = cachingService.findById(id);
        if (item.isPresent()) {
            return item.get();
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(
            tags = "Inserção de informação",
            summary = "Cadastra um novo item"
    )
    @PostMapping("/inserir")
    public Item salvar(@RequestBody Item item) {
        repository.save(item);
        cachingService.clearCache();
        return item;
    }

    @Operation(
            tags = "Atualização de informação",
            summary = "Atualiza um item já existente pelo ID"
    )
    @PutMapping(value = "/atualizar/{id}")
    public Item atualizar(@PathVariable Long id, @RequestBody Item item) {
        Optional<Item> itemOptional = repository.findById(id);
        if (itemOptional.isPresent()) {
            Item itemAtual = itemOptional.get();
            itemAtual.setNome(item.getNome());
            itemAtual.setCategoria(item.getCategoria());
            repository.save(itemAtual);
            cachingService.clearCache();
            return itemAtual;
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(
            tags = "Remoção de informação",
            summary = "Remove um item pelo ID"
    )
    @DeleteMapping("/remover/{id}")
    public Item remover(@PathVariable Long id) {
        Optional<Item> item = repository.findById(id);
        if (item.isPresent()) {
            repository.delete(item.get());
            cachingService.clearCache();
            return item.get();
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(
            tags = "Retorno de informação",
            summary = "Busca os itens e suas quantidades de um ponto de distribuição a partir do ID do ponto"
    )
    @GetMapping(value = "/buscarItensPonto")
    public List<ItemPontoProjection> buscarItemPorPonto(@RequestParam(value = "idPonto") Long idPonto) {
        return cachingService.buscarItensDoPonto(idPonto);
    }

    @Operation(
            tags = "Retorno de informação",
            summary = "Busca os itens, suas quantidades, e os pontos que estão disponíveis a partir da CATEGORIA"
    )
    @GetMapping(value = "/buscarItensDaCategoriaComEstoque")
    public List<ItemComEstoqueProjection> buscarItensDaCategoriaComEstoque(@RequestParam(value = "categoria")String categoria) {
        return cachingService.buscarItensDaCategoria(categoria);
    }
}
