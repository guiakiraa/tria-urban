package br.com.fiap.triaurban.service.paginacao;

import br.com.fiap.triaurban.dto.EstoqueItemDTO;
import br.com.fiap.triaurban.service.caching.EstoqueItemCachingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EstoqueItemPaginacaoService {
    @Autowired
    private EstoqueItemCachingService estoqueItemCachingService;

    @Transactional(readOnly = true)
    public Page<EstoqueItemDTO> paginar(PageRequest pageRequest) {
        return estoqueItemCachingService.paginar(pageRequest);
    }
}
