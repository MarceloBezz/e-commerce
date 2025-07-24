package br.com.e_commerce.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.e_commerce.demo.domain.produto.Categoria;
import br.com.e_commerce.demo.domain.produto.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
    List<Produto> findByCategoria(Categoria categoria);
}
