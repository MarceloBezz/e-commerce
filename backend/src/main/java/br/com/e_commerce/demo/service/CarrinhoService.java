package br.com.e_commerce.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.e_commerce.demo.domain.produto.DadosProdutoCarrinho;
import br.com.e_commerce.demo.domain.usuario.Usuario;
import br.com.e_commerce.demo.repository.CarrinhoRepository;
import br.com.e_commerce.demo.repository.ProdutoRepository;
import jakarta.transaction.Transactional;

@Service
public class CarrinhoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    CarrinhoRepository carrinhoRepository;

    @Transactional
    public void inserirProduto(DadosProdutoCarrinho dto, Usuario usuario) {
        var produto = produtoRepository.findById(dto.idProduto())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado!"));

        var carrinho = carrinhoRepository.findByUsuarioId(usuario.getId())
                .orElseThrow(() -> new RuntimeException("Carrinho não encontrado!"));

        // TODO: VALIDAÇÕES ANTES DE INSERIR O PRODUTO NO CARRINHO
        // - Estoque deve estar disponível
        // - O produto não deve ser do usuário interessado
        // - Verificar se o produto já não está no carrinho

        carrinho.adicionarProduto(produto, dto.quantidade());
    }

}
