package br.com.e_commerce.demo.service;

import java.beans.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.e_commerce.demo.domain.compra.Compra;
import br.com.e_commerce.demo.domain.compra.DadosCadastroCompra;
import br.com.e_commerce.demo.domain.usuario.Usuario;
import br.com.e_commerce.demo.repository.CompraRepository;
import br.com.e_commerce.demo.repository.ProdutoRepository;

@Service
public class CompraService {

    @Autowired
    private CompraRepository repository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Transient
    public void realizarCompra(Long idProduto, Usuario usuario, DadosCadastroCompra dto) {
        var produto = produtoRepository.findById(idProduto)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado!"));
        
        //TODO: VALIDAÇÕES DO PRODUTO E DO USUÁRIO:
        // - O usuário não deve ser o anunciante do produto
        // - O estoque do produto deve ser maior que zero

        produto.setQuantidade(produto.getQuantidade() - 1);
        var compra = new Compra(usuario);
        compra.adicionarItem(produto, dto.quantidade(), produto.getPreco());
        repository.save(compra);
    }

}
