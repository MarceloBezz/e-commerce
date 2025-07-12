package br.com.e_commerce.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.e_commerce.demo.domain.carrinho.Carrinho;

@Repository
public interface CarrinhoRepository extends  JpaRepository<Carrinho, Long>{

    @EntityGraph(attributePaths = "produtos")
    Optional<Carrinho> findByUsuarioId(Long usuarioId);
}
