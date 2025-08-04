package br.com.e_commerce.demo.infra.validadores;

import br.com.e_commerce.demo.domain.produto.Produto;
import br.com.e_commerce.demo.domain.usuario.Usuario;

public interface ValidarProdutoCompra {
    void validarEstoqueDisponivel(Produto produto, int quantidade);
    void validarComprador(Produto produto, Usuario usuario);
}
