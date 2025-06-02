package br.com.fiap.triaurban.service.caching;

import br.com.fiap.triaurban.dto.ItemDTO;
import br.com.fiap.triaurban.mapper.ItemMapper;
import br.com.fiap.triaurban.model.CategoriaItemEnum;
import br.com.fiap.triaurban.model.Item;
import br.com.fiap.triaurban.projection.ItemComEstoqueProjection;
import br.com.fiap.triaurban.projection.ItemPontoProjection;
import br.com.fiap.triaurban.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemCachingService {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemMapper itemMapper;

    private ItemDTO mapearParaDTO(Item item) {
        return itemMapper.toDto(item);
    }

    @Cacheable(value = "listarItens")
    public List<ItemDTO> findAll() {
        List<Item> items = itemRepository.findAll();
        return items.stream().map(this::mapearParaDTO).toList();
    }

    @Cacheable(value = "buscarItemPorId", key = "#id")
    public Optional<ItemDTO> findById(Long id) {
        return itemRepository.findById(id)
                .map(this::mapearParaDTO);
    }

    @Cacheable(value = "paginarItens", key = "#pageRequest")
    public Page<ItemDTO> paginar(PageRequest pageRequest) {
        Page<Item> items = itemRepository.findAll(pageRequest);
        return items.map(this::mapearParaDTO);
    }

    @Cacheable(value = "buscarItensDoPonto", key = "#idPonto")
    public List<ItemPontoProjection> buscarItensDoPonto(Long idPonto) {
        return itemRepository.buscarItensPorPonto(idPonto);
    }

    @Cacheable(value = "buscarItensDaCategoria", key = "#categoria")
    public List<ItemComEstoqueProjection> buscarItensDaCategoria(String categoria) {
        return itemRepository.buscarItensPorCategoria(categoria);
    }

    @CacheEvict(value = {"listarItens", "paginarItens", "buscarItemPorId", "buscarItensDoPonto", "buscarItensDaCategoria"}, allEntries = true)
    public void clearCache() {
    }
}
