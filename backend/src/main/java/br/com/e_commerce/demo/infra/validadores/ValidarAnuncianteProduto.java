package br.com.e_commerce.demo.infra.validadores;

import org.springframework.stereotype.Component;

import br.com.e_commerce.demo.domain.compra.Compra;
import br.com.e_commerce.demo.domain.produto.Produto;
import br.com.e_commerce.demo.infra.exception.RegraDeNegocioException;

@Component
public class ValidarAnuncianteProduto implements ValidadorCompra{

    @Override
    public void validar(Produto produto, Compra compra) throws Exception {
        var comprador = compra.getUsuario();

        if (comprador.getId() == produto.getAnunciante().getId()) {
            throw new RegraDeNegocioException("Você não pode comprar seu próprio produto!!");
        }
    }
    
}
