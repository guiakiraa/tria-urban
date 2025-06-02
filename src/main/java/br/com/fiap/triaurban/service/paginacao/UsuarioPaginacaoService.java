package br.com.fiap.triaurban.service.paginacao;

import br.com.fiap.triaurban.dto.UsuarioDTO;
import br.com.fiap.triaurban.service.caching.UsuarioCachingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioPaginacaoService {
    @Autowired
    private UsuarioCachingService usuarioCachingService;

    @Transactional(readOnly = true)
    public Page<UsuarioDTO> paginar(PageRequest pageRequest) {
        return usuarioCachingService.paginar(pageRequest);
    }
}
