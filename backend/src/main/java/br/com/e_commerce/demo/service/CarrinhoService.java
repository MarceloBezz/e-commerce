package br.com.e_commerce.demo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.e_commerce.demo.domain.carrinho_produto.DadosCarrinhoProduto;
import br.com.e_commerce.demo.domain.produto.DadosProdutoCarrinho;
import br.com.e_commerce.demo.domain.usuario.Usuario;
import br.com.e_commerce.demo.infra.exception.RegraDeNegocioException;
import br.com.e_commerce.demo.infra.validadores.ValidarAdicaoCarrinhoImpl;
import br.com.e_commerce.demo.repository.CarrinhoProdutoRepository;
import br.com.e_commerce.demo.repository.CarrinhoRepository;
import br.com.e_commerce.demo.repository.ProdutoRepository;
import jakarta.transaction.Transactional;

@Service
public class CarrinhoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    @Autowired
    private CarrinhoProdutoRepository repository;

    @Autowired 
    private ValidarAdicaoCarrinhoImpl validadores;

    @Transactional
    public void inserirProduto(DadosProdutoCarrinho dto, Usuario usuario) {
        var produto = produtoRepository.findById(dto.idProduto())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado!"));
        var carrinho = carrinhoRepository.findById(usuario.getCarrinho().getId()).get();

        validadores.validarComprador(produto, usuario);
        validadores.validarEstoqueDisponivel(produto, dto.quantidade());
        validadores.produtoJaAdicionado(produto, carrinho);

        carrinho.adicionarProduto(produto, dto.quantidade());
    }

    public Map<String, Object> recuperarProdutosCarrinho(Usuario usuario) {
        var carrinho = carrinhoRepository.findById(usuario.getCarrinho().getId()).get();
        List<DadosCarrinhoProduto> produtos = new ArrayList<>();
        for (var produtoCarrinho : carrinho.getProdutos()) {
                var p = produtoCarrinho.getProduto();
                produtos.add(new DadosCarrinhoProduto(p.getNome(), p.getDescricao(), produtoCarrinho.getQuantidade(), p.getPreco(), p.getAnunciante().getNome()));
        }

        Map<String, Object> resposta = new HashMap<>();
        resposta.put("produtos", produtos);
        resposta.put("valorTotal", usuario.getCarrinho().getValor());

        return resposta;
    }

    @Transactional
    public void removerProdutoDoCarrinho(Usuario usuario, Long idProduto) {
        var produto = produtoRepository.findById(idProduto)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado!"));
        var carrinho = carrinhoRepository.findById(usuario.getCarrinho().getId()).get();

        if (!repository.existsByCarrinhoAndProduto(carrinho, produto))
            throw new RegraDeNegocioException("Este produto não está em seu carrinho!");

        repository.deletarPorCarrinhoEProduto(carrinho, produto);
        carrinho.novoValorCarrinho();
    }

    @Transactional
    public void esvaziarCarrinho(Usuario usuario) {
        var carrinho = carrinhoRepository.findById(usuario.getCarrinho().getId()).get();
        carrinho.esvaziarCarrinho();
    }

}
