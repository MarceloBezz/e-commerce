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

@RestController
@RequestMapping("/compra")
public class CompraController {

    @Autowired
    private CompraService service;

    @PostMapping("/unico/{idProduto}")
    public ResponseEntity<String> comprarUmProduto(@PathVariable Long idProduto,
            @AuthenticationPrincipal Usuario usuario, @RequestBody DadosCadastroCompra dto) {
        try {
            service.realizarCompra(idProduto, usuario, dto);
            return ResponseEntity.ok().body("Compra realizada com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao realizar compra!");
        }
    }

    // TODO: Realizar compra a partir do carrinho para permitir comprar mais de um
    // produto

}
