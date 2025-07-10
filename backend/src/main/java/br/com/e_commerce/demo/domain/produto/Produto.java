package br.com.e_commerce.demo.domain.produto;

import br.com.e_commerce.demo.domain.usuario.Usuario;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "produtos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private double preco;
    private int quantidade;

    @ManyToOne
    @JoinColumn(name = "anunciante")
    private Usuario anunciante;

    public Produto(DadosCadastroProduto dto, Usuario usuario) {
        this.nome = dto.nome();
        this.descricao = dto.descricao();
        this.preco = dto.preco();
        this.quantidade = dto.quantidade();
        this.anunciante = usuario;
    }

}
