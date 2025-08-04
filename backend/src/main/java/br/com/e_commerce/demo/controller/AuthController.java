package br.com.e_commerce.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import br.com.e_commerce.demo.domain.usuario.DadosLogin;
import br.com.e_commerce.demo.service.AuthService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private AuthService service;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid DadosLogin dados) throws Exception{
        String token = service.login(dados);
        return ResponseEntity.ok("Usuário logado!\nToken: " + token);
    }

}
