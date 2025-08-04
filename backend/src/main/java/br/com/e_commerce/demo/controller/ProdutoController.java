package br.com.e_commerce.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.e_commerce.demo.domain.produto.Categoria;
import br.com.e_commerce.demo.domain.produto.DadosCadastroProduto;
import br.com.e_commerce.demo.domain.produto.DadosEdicaoProduto;
import br.com.e_commerce.demo.domain.usuario.Usuario;
import br.com.e_commerce.demo.infra.exception.RegraDeNegocioException;
import br.com.e_commerce.demo.service.ProdutoService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @PostMapping
    public ResponseEntity<Object> cadastrar(@RequestBody @Valid DadosCadastroProduto dto,
            @AuthenticationPrincipal Usuario usuario) {
        var produto = service.cadastrarProduto(dto, usuario);
        return ResponseEntity.ok().body(produto);
    }

    @GetMapping("/{idProduto}")
    public ResponseEntity<Object> pegarProduto(@RequestParam Long idProduto) {
        var produto = service.pegarProdutoPorId(idProduto);
        return ResponseEntity.ok().body(produto);
    }

    @PutMapping("/{idProduto}")
    public ResponseEntity<Object> editarProduto(@RequestParam Long idProduto, @AuthenticationPrincipal Usuario usuario,
            @RequestBody DadosEdicaoProduto dto) throws RegraDeNegocioException {
        var produtoAtualizado = service.atualizarProduto(dto, usuario, idProduto);
        return ResponseEntity.ok().body(produtoAtualizado);
    }

    @DeleteMapping("/{idProduto}")
    public ResponseEntity<Object> deletarProduto(@RequestParam Long idProduto,
            @AuthenticationPrincipal Usuario usuario) {
        service.deletarProduto(usuario, idProduto);
        return ResponseEntity.ok().body("Produto deletado com sucesso!");
    }

    @GetMapping("/categoria")
    public ResponseEntity<Object> filtrarPorCategoria(@RequestBody Categoria categoria) {
        var produtos = service.buscarPorCategoria(categoria);
        return ResponseEntity.ok(produtos);
    }
}
