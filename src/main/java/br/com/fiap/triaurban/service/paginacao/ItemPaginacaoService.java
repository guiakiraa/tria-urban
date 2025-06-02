package br.com.fiap.triaurban.service.paginacao;

import br.com.fiap.triaurban.dto.ItemDTO;
import br.com.fiap.triaurban.service.caching.ItemCachingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ItemPaginacaoService {
    @Autowired
    private ItemCachingService itemCachingService;

    @Transactional(readOnly = true)
    public Page<ItemDTO> paginar(PageRequest pageRequest) {
        return itemCachingService.paginar(pageRequest);
    }
}
