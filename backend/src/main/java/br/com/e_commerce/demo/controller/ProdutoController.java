package br.com.e_commerce.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.e_commerce.demo.domain.produto.DadosCadastroProduto;
import br.com.e_commerce.demo.domain.usuario.Usuario;
import br.com.e_commerce.demo.service.ProdutoService;
import jakarta.validation.Valid;

@RestController
public class ProdutoController {
    
    @Autowired
    private ProdutoService service;

    @PostMapping("/produtos")
    public ResponseEntity<Object> cadastrar(@RequestBody @Valid DadosCadastroProduto dto, @AuthenticationPrincipal Usuario usuario) {
        try {
            var produto = service.cadastrarProduto(dto, usuario);
            return ResponseEntity.ok().body(produto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao cadastrar produto!");
        }
    }
}
