package br.com.e_commerce.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.e_commerce.demo.domain.compra.DadosCadastroCompra;
import br.com.e_commerce.demo.domain.usuario.Usuario;
import br.com.e_commerce.demo.service.CompraService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/compra")
public class CompraController {

    @Autowired
    private CompraService service;

    @PostMapping("/unico/{idProduto}")
    public ResponseEntity<String> comprarUmProduto(@PathVariable Long idProduto,
            @AuthenticationPrincipal Usuario usuario, @RequestBody DadosCadastroCompra dto) {
        service.realizarCompra(idProduto, usuario, dto);
        return ResponseEntity.ok().body("Compra realizada com sucesso!");
    }

    @PostMapping("/carrinho")
    public ResponseEntity<String> comprarProdutosCarrinho(@AuthenticationPrincipal Usuario usuario) {
        var valorCompra = service.realizarCompraCarrinho(usuario);
        return ResponseEntity.ok().body(valorCompra);
    }

    @GetMapping("/visualizar/todas/{usuarioId}")
    public ResponseEntity<Object> visualizarTodasAsCompras(@AuthenticationPrincipal Usuario usuario,
            @PathVariable Long usuarioId) {
        var compras = service.listarCompras(usuario, usuarioId);
        return ResponseEntity.ok().body(compras);
    }

    @GetMapping("/visualizar/{idCompra}")
    public ResponseEntity<Object> visualizarCompra(@PathVariable Long idCompra,
            @AuthenticationPrincipal Usuario usuario) {
        var compra = service.visualizarCompra(idCompra, usuario);
        return ResponseEntity.ok().body(compra);
    }

}
