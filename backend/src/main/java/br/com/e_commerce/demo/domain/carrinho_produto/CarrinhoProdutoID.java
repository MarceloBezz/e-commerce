package br.com.e_commerce.demo.domain.carrinho_produto;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class CarrinhoProdutoID implements Serializable{
    
    private Long carrinhoId;
    private Long produtoId;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((carrinhoId == null) ? 0 : carrinhoId.hashCode());
        result = prime * result + ((produtoId == null) ? 0 : produtoId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CarrinhoProdutoID other = (CarrinhoProdutoID) obj;
        if (carrinhoId == null) {
            if (other.carrinhoId != null)
                return false;
        } else if (!carrinhoId.equals(other.carrinhoId))
            return false;
        if (produtoId == null) {
            if (other.produtoId != null)
                return false;
        } else if (!produtoId.equals(other.produtoId))
            return false;
        return true;
    }

    
}
