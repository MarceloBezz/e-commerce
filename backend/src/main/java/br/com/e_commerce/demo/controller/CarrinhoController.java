package br.com.e_commerce.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.e_commerce.demo.domain.produto.DadosProdutoCarrinho;
import br.com.e_commerce.demo.domain.usuario.Usuario;
import br.com.e_commerce.demo.service.CarrinhoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/carrinho")
public class CarrinhoController {

    @Autowired
    private CarrinhoService service;

    @PostMapping("/inserir")
    public ResponseEntity<Object> inserirProduto(@RequestBody DadosProdutoCarrinho dto,
            @AuthenticationPrincipal Usuario usuario) {
        try {
            service.inserirProduto(dto, usuario);
            return ResponseEntity.ok().body("Produto inserido no carrinho!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Object> recuperarProdutosCarrinho(@AuthenticationPrincipal Usuario usuario) {
        try {
            var produtos = service.recuperarProdutosCarrinho(usuario);
            return ResponseEntity.ok().body(produtos);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{idProduto}")
    public ResponseEntity<String> removerProdutoDoCarrinho(@PathVariable Long idProduto,
            @AuthenticationPrincipal Usuario usuario) {
        try {
            service.removerProdutoDoCarrinho(usuario, idProduto);
            return ResponseEntity.ok("Produto removido do carrinho!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<String> esvaziarCarrinho(@AuthenticationPrincipal Usuario usuario) {
        try {
            service.esvaziarCarrinho(usuario);
            return ResponseEntity.ok("Carrinho esvaziado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
