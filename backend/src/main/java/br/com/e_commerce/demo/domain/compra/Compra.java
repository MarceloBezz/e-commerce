package br.com.e_commerce.demo.domain.compra;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.e_commerce.demo.domain.produto.Produto;
import br.com.e_commerce.demo.domain.produto_compra.ProdutoCompra;
import br.com.e_commerce.demo.domain.usuario.Usuario;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "compras")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;

    private LocalDateTime dataCompra;
    private Double valorCompra;

    // @ManyToMany
    // @JoinTable(name = "produtos_compras", joinColumns = @JoinColumn(name = "compra_id"), inverseJoinColumns = @JoinColumn(name = "produto_id"))
    // private List<Produto> produto = new ArrayList<>();

    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProdutoCompra> produtos = new ArrayList<>();

    public void adicionarItem(Produto produto, Integer quantidade, Double precoUnitario) {
        var produtoCompra = new ProdutoCompra(this, produto, quantidade, precoUnitario);
        this.produtos.add(produtoCompra);
        valorCompra += produto.getPreco() * quantidade;
    }

    public Compra(Usuario usuarioCompra) {
        this.usuario = usuarioCompra;
        this.dataCompra = LocalDateTime.now();
        this.valorCompra = 0.0;
    }
}
