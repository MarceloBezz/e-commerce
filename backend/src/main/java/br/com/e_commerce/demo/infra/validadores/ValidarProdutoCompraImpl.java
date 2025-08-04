package br.com.e_commerce.demo.infra.validadores;

import org.springframework.stereotype.Component;

import br.com.e_commerce.demo.domain.produto.Produto;
import br.com.e_commerce.demo.domain.usuario.Usuario;
import br.com.e_commerce.demo.infra.exception.RegraDeNegocioException;

@Component
public class ValidarProdutoCompraImpl implements ValidarProdutoCompra {

    @Override
    public void validarEstoqueDisponivel(Produto produto, int quantidade) {
        if (quantidade > produto.getQuantidade()) {
            throw new RegraDeNegocioException("O número de produtos solicitados excede o atual estoque do produto!");
        }
    }

    @Override
    public void validarComprador(Produto produto, Usuario usuario) {
        if (usuario.getId() == produto.getAnunciante().getId()) {
            throw new RegraDeNegocioException("Você não pode comprar seu próprio produto!");
        }
    }

}
