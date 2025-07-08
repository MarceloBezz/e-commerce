package br.com.e_commerce.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.e_commerce.demo.domain.perfil.Perfil;
import br.com.e_commerce.demo.domain.perfil.PerfilEnum;


public interface PerfilRepository extends JpaRepository<Perfil, Long>{
    Perfil findByNome(PerfilEnum nome);
}
