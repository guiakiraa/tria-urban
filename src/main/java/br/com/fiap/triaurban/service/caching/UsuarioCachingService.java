package br.com.fiap.triaurban.service.caching;

import br.com.fiap.triaurban.dto.UsuarioDTO;
import br.com.fiap.triaurban.mapper.UsuarioMapper;
import br.com.fiap.triaurban.model.Usuario;
import br.com.fiap.triaurban.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioCachingService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;

    private UsuarioDTO mapearParaDTO(Usuario usuario) {
        return usuarioMapper.toDto(usuario);
    }

    @Cacheable(value = "listarUsuarios")
    public List<UsuarioDTO> findAll() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream().map(this::mapearParaDTO).toList();
    }

    @Cacheable(value = "buscarUsuarioPorId", key = "#id")
    public Optional<UsuarioDTO> findById(Long id) {
        return usuarioRepository.findById(id)
                .map(this::mapearParaDTO);
    }

    @Cacheable(value = "paginarUsuarios", key = "#pageRequest")
    public Page<UsuarioDTO> paginar(PageRequest pageRequest) {
        Page<Usuario> usuarios = usuarioRepository.findAll(pageRequest);
        return usuarios.map(this::mapearParaDTO);
    }

    @Cacheable(value = "buscarPorUsername", key = "#username")
    public Optional<UsuarioDTO> findByUsername(String username) {
        return usuarioRepository.findByUsername(username).map(this::mapearParaDTO);
    }

    @CacheEvict(value = {"listarUsuarios", "paginarUsuarios", "buscarUsuarioPorId", "buscarPorUsername"}, allEntries = true)
    public void clearCache() {
    }
}
