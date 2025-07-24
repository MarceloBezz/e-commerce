package br.com.e_commerce.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.e_commerce.demo.domain.carrinho.Carrinho;
import br.com.e_commerce.demo.domain.carrinho_produto.CarrinhoProduto;
import br.com.e_commerce.demo.domain.carrinho_produto.CarrinhoProdutoID;
import br.com.e_commerce.demo.domain.produto.Produto;

@Repository
public interface CarrinhoProdutoRepository extends JpaRepository<CarrinhoProduto, CarrinhoProdutoID>{
    boolean existsByCarrinhoAndProduto(Carrinho carrinho, Produto produto);

    @Modifying
    @Query("DELETE FROM CarrinhoProduto cp WHERE cp.carrinho = :carrinho AND cp.produto = :produto")
    void deletarPorCarrinhoEProduto(@Param("carrinho") Carrinho carrinho, @Param("produto") Produto produto);

    @Modifying
    @Query("DELETE FROM CarrinhoProduto cp WHERE cp.carrinho = :carrinho")
    void esvaziarCarrinho(@Param("carrinho") Carrinho carrinho);
}
