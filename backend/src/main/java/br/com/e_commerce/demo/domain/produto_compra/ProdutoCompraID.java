package br.com.e_commerce.demo.domain.produto_compra;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
public class ProdutoCompraID implements Serializable {

    private Long compraId;
    private Long produtoId;

    public ProdutoCompraID(Long compraId, Long produtoId) {
        this.compraId = compraId;
        this.produtoId = produtoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof ProdutoCompraID))
            return false;
        ProdutoCompraID that = (ProdutoCompraID) o;
        return Objects.equals(compraId, that.compraId) &&
                Objects.equals(produtoId, that.produtoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(compraId, produtoId);
    }

}
