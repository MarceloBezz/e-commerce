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
    private Double preco;
    private Integer quantidade;

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

    public void atualizarProduto(DadosEdicaoProduto dto) {
        if (dto.nome() != null && dto.nome() != "") {
            this.nome = dto.nome();
        }
        if (dto.descricao() != null && dto.descricao() != "") {
            this.descricao = dto.descricao();
        }
        if (dto.preco() != null && dto.preco() > 0) {
            this.preco = dto.preco();
        }
        if (dto.quantidade() != null && dto.quantidade() > 0) {
            this.quantidade = dto.quantidade();
        }
    }

}
