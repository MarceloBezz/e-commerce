package br.com.e_commerce.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.e_commerce.demo.domain.perfil.Perfil;
import br.com.e_commerce.demo.domain.perfil.PerfilEnum;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long>{
    Perfil findByNome(PerfilEnum nome);
}
