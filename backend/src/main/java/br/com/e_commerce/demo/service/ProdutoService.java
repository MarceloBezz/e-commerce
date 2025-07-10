package br.com.e_commerce.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.e_commerce.demo.domain.produto.DadosCadastroProduto;
import br.com.e_commerce.demo.domain.produto.DadosProduto;
import br.com.e_commerce.demo.domain.produto.Produto;
import br.com.e_commerce.demo.domain.usuario.Usuario;
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

}
