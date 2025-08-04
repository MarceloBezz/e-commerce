package br.com.e_commerce.demo.service;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.e_commerce.demo.domain.produto.Categoria;
import br.com.e_commerce.demo.domain.produto.DadosCadastroProduto;
import br.com.e_commerce.demo.domain.produto.DadosEdicaoProduto;
import br.com.e_commerce.demo.domain.produto.DadosProduto;
import br.com.e_commerce.demo.domain.produto.Produto;
import br.com.e_commerce.demo.domain.usuario.Usuario;
import br.com.e_commerce.demo.infra.exception.RegraDeNegocioException;
import br.com.e_commerce.demo.repository.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public DadosProduto cadastrarProduto(DadosCadastroProduto dto, Usuario usuario) {
        var produto = new Produto(dto, usuario);
        repository.save(produto);
        return new DadosProduto(produto);
    }

    public DadosProduto pegarProdutoPorId(Long idProduto) {
        var produto = repository.findById(idProduto)
                .orElseThrow(() -> new RegraDeNegocioException("Produto não encontrado!"));
        return new DadosProduto(produto);
    }

    @Transient
    public DadosProduto atualizarProduto(DadosEdicaoProduto dto, Usuario usuario, Long idProduto) throws RegraDeNegocioException {
        var produto = repository.findById(idProduto)
                .orElseThrow(() -> new RegraDeNegocioException("Produto não encontrado!"));
        
        if (produto.getAnunciante().getId() != usuario.getId()) {
            throw new RegraDeNegocioException("Você não tem permissão para editar esse produto!");
        }

        produto.atualizarProduto(dto);
        return new DadosProduto(produto);
    }

    @Transient
    public void deletarProduto(Usuario usuario, Long idProduto) throws RegraDeNegocioException {
        var produto = repository.findById(idProduto)
                .orElseThrow(() -> new RegraDeNegocioException("Produto não encontrado!"));
        
        if (produto.getAnunciante().getId() != usuario.getId()) {
            throw new RegraDeNegocioException("Você não tem permissão para deletar esse produto!");
        }

        repository.deleteById(idProduto);
    }

    public List<DadosProduto> buscarPorCategoria(Categoria categoria) {
        var produtos = repository.findByCategoria(categoria);

        List<DadosProduto> lista = new ArrayList<>();
        for (Produto produto : produtos) {
            lista.add(new DadosProduto(produto));
        }

        return lista;
    }

}
