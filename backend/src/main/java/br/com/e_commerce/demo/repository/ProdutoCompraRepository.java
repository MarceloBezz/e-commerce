package br.com.e_commerce.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.e_commerce.demo.domain.compra.Compra;
import br.com.e_commerce.demo.domain.produto.Produto;
import br.com.e_commerce.demo.domain.produto_compra.ProdutoCompra;
import br.com.e_commerce.demo.domain.produto_compra.ProdutoCompraID;

@Repository
public interface ProdutoCompraRepository extends JpaRepository<ProdutoCompra, ProdutoCompraID>{
    ProdutoCompra findByCompraAndProduto(Compra compra, Produto produto);
}
