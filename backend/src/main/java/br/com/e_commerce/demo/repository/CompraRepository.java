package br.com.e_commerce.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.e_commerce.demo.domain.compra.Compra;

public interface CompraRepository extends JpaRepository<Compra, Long>{
    
}
