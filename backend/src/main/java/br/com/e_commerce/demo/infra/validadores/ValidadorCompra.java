package br.com.e_commerce.demo.infra.validadores;

import br.com.e_commerce.demo.domain.compra.Compra;
import br.com.e_commerce.demo.domain.produto.Produto;

public interface ValidadorCompra {
    void validar(Produto produto, Compra compra) throws Exception;
}
