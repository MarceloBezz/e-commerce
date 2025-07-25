package br.com.e_commerce.demo.domain.compra;

import java.time.LocalDateTime;
import java.util.List;

public record DadosCompra(
    String comprador,
    Double valorCompra,
    LocalDateTime dataCompra,
    List<DadosProdutoComprado> produtos
) {
    public DadosCompra(Compra compra, List<DadosProdutoComprado> produtosCompra) {
        this(compra.getUsuario().getNome(), compra.getValorCompra(), compra.getDataCompra(), produtosCompra);
    }
}
