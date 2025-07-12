package br.com.e_commerce.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.e_commerce.demo.domain.usuario.DadosCadastroUsuario;
import br.com.e_commerce.demo.service.UsuarioService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping("/cadastro")
    public ResponseEntity<Object> cadastrar(@RequestBody @Valid DadosCadastroUsuario dados) {
        try {
            var usuario = service.cadastrar(dados);
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body("Erro ao cadastrar usuário!");
        }
    }

    @GetMapping
    public ResponseEntity<Object> listarTodos() {
        try {
            var usuarios = service.listarTodos();
            return ResponseEntity.ok(usuarios);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao listar usuários!");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> pegarPorId(@PathVariable Long id) {
        try {
            var usuario = service.pegarPorId(id);
            return ResponseEntity.ok(usuario);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao buscar usuário!");
        }
    }
}
