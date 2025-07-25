package br.com.e_commerce.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.e_commerce.demo.domain.compra.Compra;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long>{

    List<Compra> findByUsuarioId(Long id);

    Optional<Compra> findByIdAndUsuarioId(Long id, Long usuarioId);
}
