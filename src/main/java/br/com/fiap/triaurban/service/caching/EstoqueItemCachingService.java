package br.com.fiap.triaurban.service.caching;

import br.com.fiap.triaurban.dto.EstoqueItemDTO;
import br.com.fiap.triaurban.mapper.EstoqueItemMapper;
import br.com.fiap.triaurban.model.EstoqueItem;
import br.com.fiap.triaurban.repository.EstoqueItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstoqueItemCachingService {
    @Autowired
    private EstoqueItemRepository estoqueItemRepository;

    @Autowired
    private EstoqueItemMapper estoqueItemMapper;

    private EstoqueItemDTO mapearParaDTO(EstoqueItem estoqueItem) {
        return estoqueItemMapper.toDto(estoqueItem);
    }

    @Cacheable(value = "listarEstoques")
    public List<EstoqueItemDTO> findAll() {
        List<EstoqueItem> estoqueItems = estoqueItemRepository.findAll();
        return estoqueItems.stream().map(this::mapearParaDTO).toList();
    }

    @Cacheable(value = "buscarEstoquePorId", key = "#id")
    public Optional<EstoqueItemDTO> findById(Long id) {
        return estoqueItemRepository.findById(id)
                .map(this::mapearParaDTO);
    }

    @Cacheable(value = "paginarEstoques", key = "#pageRequest")
    public Page<EstoqueItemDTO> paginar(PageRequest pageRequest) {
        Page<EstoqueItem> estoqueItems = estoqueItemRepository.findAll(pageRequest);
        return estoqueItems.map(this::mapearParaDTO);
    }

    @CacheEvict(value = {"listarEstoques", "paginarEstoques", "buscarEstoquePorId"}, allEntries = true)
    public void clearCache() {
    }
}
