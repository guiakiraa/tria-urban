package br.com.fiap.triaurban.controller;

import br.com.fiap.triaurban.dto.UsuarioDTO;
import br.com.fiap.triaurban.model.Usuario;
import br.com.fiap.triaurban.repository.UsuarioRepository;
import br.com.fiap.triaurban.service.caching.UsuarioCachingService;
import br.com.fiap.triaurban.service.paginacao.UsuarioPaginacaoService;
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
@RequestMapping(value = "/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private UsuarioCachingService cachingService;

    @Autowired
    private UsuarioPaginacaoService paginacaoService;

    @Operation(
            tags = "Retorno de informação",
            summary = "Busca todos os usuários"
    )
    @GetMapping(value = "/listar")
    public List<UsuarioDTO> listar() {
        return cachingService.findAll();
    }

    @Operation(
            tags = "Retorno de informação",
            summary = "Busca todos os usuários e exibe em forma de páginas. Evitando muitos resultados de um vez"
    )
    @GetMapping("/paginadas")
    public ResponseEntity<Page<UsuarioDTO>> paginar(
            @RequestParam(value = "pagina", defaultValue = "0") Integer page,
            @RequestParam(value = "tamanho", defaultValue = "2") Integer size
    ) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<UsuarioDTO> usuariosPaginados = paginacaoService.paginar(pageRequest);
        return new ResponseEntity<>(usuariosPaginados, HttpStatus.OK);
    }

    @Operation(
            tags = "Retorno de informação",
            summary = "Busca um usuário pelo ID"
    )
    @GetMapping(value = "/{id}")
    public UsuarioDTO buscarPorId(@PathVariable Long id) {
        Optional<UsuarioDTO> endereco = cachingService.findById(id);
        if (endereco.isPresent()) {
            return endereco.get();
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(
            tags = "Inserção de informação",
            summary = "Cadastra um novo usuário"
    )
    @PostMapping("/inserir")
    public Usuario salvar(@RequestBody Usuario usuario) {
        repository.save(usuario);
        cachingService.clearCache();
        return usuario;
    }

    @Operation(
            tags = "Atualização de informação",
            summary = "Atualiza um usuário já existente pelo ID"
    )
    @PutMapping(value = "/atualizar/{id}")
    public Usuario atualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
        Optional<Usuario> usuarioOptional = repository.findById(id);
        if (usuarioOptional.isPresent()) {
            Usuario usuarioAtual = usuarioOptional.get();
            usuarioAtual.setNomeCompleto(usuario.getNomeCompleto());
            usuarioAtual.setEmail(usuario.getEmail());
            usuarioAtual.setSenha(usuario.getSenha());
            usuarioAtual.setUsername(usuario.getUsername());
            repository.save(usuarioAtual);
            cachingService.clearCache();
            return usuarioAtual;
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(
            tags = "Remoção de informação",
            summary = "Remove um usuário pelo ID"
    )
    @DeleteMapping("/remover/{id}")
    public Usuario remover(@PathVariable Long id) {
        Optional<Usuario> usuario = repository.findById(id);
        if (usuario.isPresent()) {
            repository.delete(usuario.get());
            cachingService.clearCache();
            return usuario.get();
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(
            tags = "Retorno de informação",
            summary = "Busca o usuário pelo username"
    )
    @GetMapping(value = "/buscarPorUsername")
    public UsuarioDTO buscarPorUsername(@RequestParam(value = "username") String username) {
        Optional<UsuarioDTO> usuario = cachingService.findByUsername(username);
        if (usuario.isPresent()) {
            return usuario.get();
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
