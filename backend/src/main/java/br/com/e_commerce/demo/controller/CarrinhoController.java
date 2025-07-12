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


@RestController
@RequestMapping("/carrinho")
public class CarrinhoController {

    @Autowired
    private CarrinhoService service;
    
    @PostMapping("/inserir")
    public ResponseEntity<Object> inserirProduto(@RequestBody DadosProdutoCarrinho dto, @AuthenticationPrincipal Usuario usuario) {
        try {
            service.inserirProduto(dto, usuario);
            return ResponseEntity.ok().body("Produto inserido no carrinho!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
}
