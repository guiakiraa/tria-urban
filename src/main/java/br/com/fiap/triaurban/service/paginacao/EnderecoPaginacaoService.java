package br.com.fiap.triaurban.service.paginacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.com.fiap.triaurban.service.caching.EnderecoCachingService;
import br.com.fiap.triaurban.dto.EnderecoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@Service
public class EnderecoPaginacaoService {
    @Autowired
    private EnderecoCachingService enderecoCachingService;

    @Transactional(readOnly = true)
    public Page<EnderecoDTO> paginar(PageRequest pageRequest) {
        return enderecoCachingService.paginar(pageRequest);
    }
}
