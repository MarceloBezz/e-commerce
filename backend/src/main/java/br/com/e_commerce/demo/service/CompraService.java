package br.com.e_commerce.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.e_commerce.demo.domain.compra.Compra;
import br.com.e_commerce.demo.domain.compra.DadosCadastroCompra;
import br.com.e_commerce.demo.domain.compra.DadosCompra;
import br.com.e_commerce.demo.domain.compra.DadosProdutoComprado;
import br.com.e_commerce.demo.domain.perfil.PerfilEnum;
import br.com.e_commerce.demo.domain.usuario.Usuario;
import br.com.e_commerce.demo.infra.exception.RegraDeNegocioException;
import br.com.e_commerce.demo.repository.CarrinhoRepository;
import br.com.e_commerce.demo.repository.CompraRepository;
import br.com.e_commerce.demo.repository.PerfilRepository;
import br.com.e_commerce.demo.repository.ProdutoRepository;
import br.com.e_commerce.demo.repository.UsuarioRepository;
import jakarta.transaction.Transactional;

@Service
public class CompraService {

    @Autowired
    private CompraRepository repository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    @Transactional
    public void realizarCompra(Long idProduto, Usuario usuario, DadosCadastroCompra dto) {
        var produto = produtoRepository.findById(idProduto)
                .orElseThrow(() -> new RegraDeNegocioException("Produto não encontrado!"));

        // TODO: VALIDAÇÕES DO PRODUTO E DO USUÁRIO:
        // - O usuário não deve ser o anunciante do produto
        // - O estoque do produto deve ser maior que zero

        produto.setQuantidade(produto.getQuantidade() - 1);
        var compra = new Compra(usuario);
        compra.adicionarItem(produto, dto.quantidade(), produto.getPreco());
        repository.save(compra);
    }

    @Transactional
    public String realizarCompraCarrinho(Usuario usuario) {
        var carrinho = carrinhoRepository
                .findById(usuario.getCarrinho().getId())
                .get();

        if (carrinho.getProdutos().size() == 0)
            throw new RegraDeNegocioException("Você não tem produtos no carrinho!");

        var compra = new Compra(usuario);
        for (var carrinhoProduto : carrinho.getProdutos()) {
            compra.adicionarItem(carrinhoProduto.getProduto(), carrinhoProduto.getQuantidade(),
                    carrinhoProduto.getProduto().getPreco());
            // Remover do estoque a quantidade comprada
            carrinhoProduto.getProduto()
                    .setQuantidade(carrinhoProduto.getProduto().getQuantidade() - carrinhoProduto.getQuantidade());
        }

        repository.save(compra);
        carrinho.esvaziarCarrinho();

        return "Compra fechada no valor de %.2f".formatted(compra.getValorCompra());
    }

    public List<DadosCompra> listarCompras(Usuario usuarioLogado, Long usuarioId) {
        if (!validarPermissaoUsuario(usuarioLogado, usuarioId, PerfilEnum.ADMINISTRADOR))
            throw new RegraDeNegocioException("Você não tem permissão para acessar esse conteúdo!");

        var compras = repository.findByUsuarioId(usuarioId);

        List<DadosCompra> dadosCompras = new ArrayList<>();
        for (var compra : compras) {
            var dadosProdutosComprados = compra.getProdutos()
                    .stream()
                    .map(pc -> new DadosProdutoComprado(pc.getProduto(), pc.getQuantidade()))
                    .toList();

            dadosCompras.add(new DadosCompra(compra, dadosProdutosComprados));
        }

        return dadosCompras;
    }

    public DadosCompra visualizarCompra(Long idCompra, Usuario usuarioLogado) {
        var compra = repository.findById(idCompra)
            .orElseThrow(() -> new RegraDeNegocioException("Compra não encontrada!"));

        var comprador = compra.getUsuario().getId();
        if (!validarPermissaoUsuario(usuarioLogado, comprador, PerfilEnum.ADMINISTRADOR))
            throw new RegraDeNegocioException("Você não tem permissão para acessar esse conteúdo!");

        var dadosProdutosComprados = compra.getProdutos()
                .stream()
                .map(pc -> new DadosProdutoComprado(pc.getProduto(), pc.getQuantidade()))
                .toList();

        return new DadosCompra(compra, dadosProdutosComprados);
    }

    //TODO: Deixar essa validação em um arquivo separado
    private boolean validarPermissaoUsuario(Usuario usuarioLogado, Long id, PerfilEnum role) {
        var usuarioBD = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        if (!usuarioBD.getId().equals(usuarioLogado.getId())) {
            var perfil = perfilRepository.findByNome(role);

            boolean temPermissao = usuarioLogado
                    .getAuthorities()
                    .stream()
                    .anyMatch(x -> x.getAuthority().equals("ROLE_" + perfil.getNome()));

            if (!temPermissao) {
                return false;
            }
        }
        return true;
    }

}
