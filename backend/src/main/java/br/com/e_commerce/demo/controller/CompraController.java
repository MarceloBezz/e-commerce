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
        try {
            service.realizarCompra(idProduto, usuario, dto);
            return ResponseEntity.ok().body("Compra realizada com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao realizar compra!");
        }
    }

    @PostMapping("/carrinho")
    public ResponseEntity<String> comprarProdutosCarrinho(@AuthenticationPrincipal Usuario usuario) {
        try {
            var valorCompra = service.realizarCompraCarrinho(usuario);
            return ResponseEntity.ok().body(valorCompra);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao realizar compra!");
        }
    }

    @GetMapping("/visualizar/todas/{usuarioId}")
    public ResponseEntity<Object> visualizarTodasAsCompras(@AuthenticationPrincipal Usuario usuario,
            @PathVariable Long usuarioId) {
        try {
            var compras = service.listarCompras(usuario, usuarioId);

            if (compras.size() == 0)
                return ResponseEntity.ok().body("Você ainda não fez nenhuma compra!");

            return ResponseEntity.ok().body(compras);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao listar compras!");
        }
    }

    @GetMapping("/visualizar/{idCompra}")
    public ResponseEntity<Object> visualizarCompra(@PathVariable Long idCompra,
            @AuthenticationPrincipal Usuario usuario) {
        try {
            var compra = service.visualizarCompra(idCompra, usuario);

            return ResponseEntity.ok().body(compra);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao visualizar compra!");
        }
    }

}
