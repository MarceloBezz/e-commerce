package br.com.e_commerce.demo.service;

import static org.mockito.ArgumentMatchers.any;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.e_commerce.demo.domain.produto.Categoria;
import br.com.e_commerce.demo.domain.produto.DadosCadastroProduto;
import br.com.e_commerce.demo.domain.produto.DadosEdicaoProduto;
import br.com.e_commerce.demo.domain.produto.Produto;
import br.com.e_commerce.demo.domain.usuario.Usuario;
import br.com.e_commerce.demo.infra.exception.RegraDeNegocioException;
import br.com.e_commerce.demo.repository.ProdutoRepository;

@ExtendWith(MockitoExtension.class)
public class ProdutoServiceTest {

    @Mock
    private ProdutoRepository repository;

    @Mock
    private DadosCadastroProduto dto;

    @Mock
    private DadosEdicaoProduto dtoEdicao;

    @InjectMocks
    private ProdutoService service;

    @Mock
    private Usuario usuario;

    @Test
    void deveriaCadastrarProduto() {
        // ARRANGE
        dto = new DadosCadastroProduto("Produto teste", "Produto a ser testado", 29.99, 14, Categoria.UTENSILIO);
        var produtoSalvo = new Produto(dto, usuario);
        BDDMockito.given(repository.save(any(Produto.class))).willReturn(produtoSalvo);

        // ACT
        var produtoRetornado = service.cadastrarProduto(dto, usuario);

        // ASSERT
        Assertions.assertEquals(produtoRetornado.nome(), dto.nome());
        Assertions.assertEquals(produtoRetornado.descricao(), dto.descricao());

        BDDMockito.verify(repository).save(any(Produto.class));
    }

    @Test
    void deveriaAtualizarProduto() {
        // ARRANGE
        dtoEdicao = new DadosEdicaoProduto("Novo nome do produto", "Nova descrição", 47.90, 70);
        var produtoSalvo = new Produto(1L, "Nome antigo do produto", "Descrição antiga do produto", 12.80, 3,
                Categoria.CALCADO, usuario);
        BDDMockito.given(repository.findById(produtoSalvo.getId())).willReturn(Optional.of(produtoSalvo));

        // ACT
        var produtoRetornado = service.atualizarProduto(dtoEdicao, usuario, 1L);

        // ASSERT
        Assertions.assertEquals(produtoRetornado.nome(), dtoEdicao.nome());
        Assertions.assertEquals(produtoRetornado.descricao(), dtoEdicao.descricao());
        Assertions.assertEquals(produtoRetornado.preco(), dtoEdicao.preco());
        Assertions.assertEquals(produtoRetornado.quantidade(), dtoEdicao.quantidade());
    }

    @Test
    void naoDeveriaAtualizarProdutoDeOutroUsuario() {
        // ARRANGE
        dtoEdicao = new DadosEdicaoProduto("Novo nome do produto", "Nova descrição", 47.90, 70);

        Usuario novoUsuario = new Usuario();
        novoUsuario.setId(2L);
        usuario.setId(1L);

        var produtoSalvo = new Produto(1L, "Nome antigo do produto", "Descrição antiga do produto", 12.80, 3,
                Categoria.CALCADO, novoUsuario);
        BDDMockito.given(repository.findById(produtoSalvo.getId())).willReturn(Optional.of(produtoSalvo));

        // ACT & ASSERT
        Assertions.assertThrows(RegraDeNegocioException.class,
                () -> service.atualizarProduto(dtoEdicao, usuario, 1L));
    }

    @Test
    void naoDeveriaDeletarProdutoDeOutroUsuario() {
        //ARRANGE
        Usuario novoUsuario = new Usuario();
        novoUsuario.setId(2L);
        usuario.setId(1L);
        var produtoSalvo = new Produto(1L, "Nome antigo do produto", "Descrição antiga do produto", 12.80, 3,
                Categoria.CALCADO, novoUsuario);

                // ACT & ASSERT
        Assertions.assertThrows(RegraDeNegocioException.class,
                () -> service.deletarProduto(usuario, produtoSalvo.getId()));
    }
}
