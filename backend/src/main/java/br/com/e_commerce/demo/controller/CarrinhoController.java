package br.com.e_commerce.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.e_commerce.demo.domain.produto.DadosProdutoCarrinho;
import br.com.e_commerce.demo.domain.usuario.Usuario;
import br.com.e_commerce.demo.infra.exception.RegraDeNegocioException;
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
            @AuthenticationPrincipal Usuario usuario) throws RegraDeNegocioException, Exception {
        service.inserirProduto(dto, usuario);
        return ResponseEntity.ok().body("Produto inserido no carrinho!");
    }

    @GetMapping
    public ResponseEntity<Object> recuperarProdutosCarrinho(@AuthenticationPrincipal Usuario usuario) {
        var produtos = service.recuperarProdutosCarrinho(usuario);
        return ResponseEntity.ok().body(produtos);
    }

    @DeleteMapping("/{idProduto}")
    public ResponseEntity<String> removerProdutoDoCarrinho(@PathVariable Long idProduto,
            @AuthenticationPrincipal Usuario usuario) throws RegraDeNegocioException {
        service.removerProdutoDoCarrinho(usuario, idProduto);
        return ResponseEntity.ok("Produto removido do carrinho!");
    }

    @DeleteMapping
    public ResponseEntity<String> esvaziarCarrinho(@AuthenticationPrincipal Usuario usuario) {
        service.esvaziarCarrinho(usuario);
        return ResponseEntity.ok("Carrinho esvaziado com sucesso!");
    }
}
