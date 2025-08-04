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

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping("/cadastro")
    public ResponseEntity<Object> cadastrar(@RequestBody @Valid DadosCadastroUsuario dados) {
        var usuario = service.cadastrar(dados);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping
    public ResponseEntity<Object> listarTodos() {
        var usuarios = service.listarTodos();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> pegarPorId(@PathVariable Long id) {
        var usuario = service.pegarPorId(id);
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarUsuario(@PathVariable Long id) {
        service.deletarUsuario(id);
        return ResponseEntity.ok("Usuário deletado com sucesso!");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> reativarUsuario(@PathVariable Long id) {
        service.reativarUsuario(id);
        return ResponseEntity.ok("Usuário reativado com sucesso!");
    }
}
