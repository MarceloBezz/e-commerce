package br.com.e_commerce.demo.domain.carrinho;

import java.util.ArrayList;
import java.util.List;

import br.com.e_commerce.demo.domain.carrinho_produto.CarrinhoProduto;
import br.com.e_commerce.demo.domain.produto.Produto;
import br.com.e_commerce.demo.domain.usuario.Usuario;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "carrinhos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Carrinho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double valor;

    @OneToOne
    @JoinColumn(unique = true)
    private Usuario usuario;

    @OneToMany(mappedBy = "carrinho", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CarrinhoProduto> produtos = new ArrayList<>();

    public void adicionarProduto(Produto produto, int quantidade) {
        this.produtos.add(new CarrinhoProduto(this, produto, quantidade));
        this.valor += (produto.getPreco() * quantidade);
    }

    public Carrinho(Usuario usuario) {
        this.usuario = usuario;
    }

    public void novoValorCarrinho() {
        this.valor = 0.0;
        
        if (produtos.size() == 0)
            return;

        for (var produto : this.produtos) {
            this.valor += produto.getProduto().getPreco() * produto.getQuantidade();
        }
    }

    public void esvaziarCarrinho() {
        produtos.clear();
        this.valor = 0.0;
    }
}
