package br.com.fiap.triaurban.service.caching;

import br.com.fiap.triaurban.dto.EnderecoDTO;
import br.com.fiap.triaurban.mapper.EnderecoMapper;
import br.com.fiap.triaurban.model.Endereco;
import br.com.fiap.triaurban.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoCachingService {
    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private EnderecoMapper enderecoMapper;

    private EnderecoDTO mapearParaDTO(Endereco endereco) {
        return enderecoMapper.toDto(endereco);
    }

    @Cacheable(value = "listarEnderecos")
    public List<EnderecoDTO> findAll() {
        List<Endereco> enderecos = enderecoRepository.findAll();
        return enderecos.stream().map(this::mapearParaDTO).toList();
    }

    @Cacheable(value = "buscarEnderecoPorId", key = "#id")
    public Optional<EnderecoDTO> findById(Long id) {
        return enderecoRepository.findById(id)
                .map(this::mapearParaDTO);
    }

    @Cacheable(value = "paginarEnderecos", key = "#pageRequest")
    public Page<EnderecoDTO> paginar(PageRequest pageRequest) {
        Page<Endereco> enderecos = enderecoRepository.findAll(pageRequest);
        return enderecos.map(this::mapearParaDTO);
    }

    @CacheEvict(value = {"listarEnderecos", "paginarEnderecos", "buscarEnderecoPorId"}, allEntries = true)
    public void clearCache() {
    }
}
