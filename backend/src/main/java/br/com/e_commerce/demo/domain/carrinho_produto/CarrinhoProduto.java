package br.com.e_commerce.demo.domain.carrinho_produto;

import br.com.e_commerce.demo.domain.carrinho.Carrinho;
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
@Table(name = "carrinhos_produtos")
@Getter
@Setter
@NoArgsConstructor
public class CarrinhoProduto {
    
    @EmbeddedId
    private CarrinhoProdutoID id = new CarrinhoProdutoID();

    @ManyToOne
    @MapsId("carrinhoId")
    @JoinColumn(name = "carrinho_id")
    private Carrinho carrinho;

    @ManyToOne
    @MapsId("produtoId")
    @JoinColumn(name = "produto_id")
    private Produto produto;

    Integer quantidade;

    public CarrinhoProduto(Carrinho carrinho, Produto produto, Integer quantidade) {
        this.carrinho = carrinho;
        this.produto = produto;
        this.quantidade = quantidade;
    
        this.id = new CarrinhoProdutoID(carrinho.getId(), produto.getId());
    }
}
