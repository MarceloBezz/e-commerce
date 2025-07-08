package br.com.e_commerce.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.e_commerce.demo.domain.usuario.DadosCadastro;
import br.com.e_commerce.demo.domain.usuario.DadosUsuario;
import br.com.e_commerce.demo.service.UsuarioService;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService service;
    
    @PostMapping("/cadastro")
    public ResponseEntity<DadosUsuario> cadastrar(@RequestBody DadosCadastro dados) {
        var usuario = service.cadastrar(dados);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/usuarios")
    public ResponseEntity listarTodos() {
        var usuarios = service.listarTodos();
        return ResponseEntity.ok(usuarios);
    }
    
    
}
