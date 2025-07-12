package br.com.e_commerce.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.e_commerce.demo.domain.compra.Compra;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long>{
    
}
