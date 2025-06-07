package br.com.fiap.triaurban.controller;

import br.com.fiap.triaurban.security.JWTUtil;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/autenticacao")
public class AutenticacaoController {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Operation(
            tags = "Autenticação",
            summary = "Este endpoint faz sua autenticação na API gerando um Token JWT, possibilitando a utilização dos outros endpoints"
    )
    @PostMapping("/login")
    public Map<String, String> gerarTokenValido(@RequestParam String username, @RequestParam String password) {
        Map<String, String> response = new HashMap<>();
        try {
            var auth = new UsernamePasswordAuthenticationToken(username, password);
            authenticationManager.authenticate(auth);
            String token = jwtUtil.construirToken(username);
            response.put("token", token);
            return response;
        } catch (Exception e) {
            response.put("error", "Usuário ou senha inválidos");
            return response;
        }
    }

}
