package br.com.e_commerce.demo.domain.produto_compra;

import br.com.e_commerce.demo.domain.compra.Compra;
import br.com.e_commerce.demo.domain.produto.Produto;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "produtos_compras")
@Getter
@Setter
@NoArgsConstructor
public class ProdutoCompra {
    
    @EmbeddedId
    private ProdutoCompraID id = new ProdutoCompraID();

    @ManyToOne
    @MapsId("compraId")
    @JoinColumn(name = "compra_id")
    private Compra compra;

    @ManyToOne
    @MapsId("produtoId")
    @JoinColumn(name = "produto_id")
    private Produto produto;

    private Integer quantidade;
    private Double precoUnitario;

    public ProdutoCompra(Compra compra, Produto produto, Integer quantidade, Double precoUnitario) {
        this.compra = compra;
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;

        this.id = new ProdutoCompraID(compra.getId(), produto.getId());
    }
}
