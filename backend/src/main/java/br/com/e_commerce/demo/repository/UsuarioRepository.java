package br.com.e_commerce.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.e_commerce.demo.domain.usuario.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    Optional<Usuario>  findByEmailIgnoreCase(String email);
}
