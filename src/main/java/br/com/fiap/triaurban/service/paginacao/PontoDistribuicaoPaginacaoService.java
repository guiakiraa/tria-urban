package br.com.fiap.triaurban.service.paginacao;

import br.com.fiap.triaurban.dto.PontoDistribuicaoDTO;
import br.com.fiap.triaurban.service.caching.PontoDistribuicaoCachingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PontoDistribuicaoPaginacaoService {
    @Autowired
    private PontoDistribuicaoCachingService pontoDistribuicaoCachingService;

    @Transactional(readOnly = true)
    public Page<PontoDistribuicaoDTO> paginar(PageRequest pageRequest) {
        return pontoDistribuicaoCachingService.paginar(pageRequest);
    }
}
