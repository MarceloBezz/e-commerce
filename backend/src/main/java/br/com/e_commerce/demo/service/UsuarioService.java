package br.com.e_commerce.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.e_commerce.demo.domain.perfil.PerfilEnum;
import br.com.e_commerce.demo.domain.usuario.DadosCadastroUsuario;
import br.com.e_commerce.demo.domain.usuario.DadosUsuario;
import br.com.e_commerce.demo.domain.usuario.Usuario;
import br.com.e_commerce.demo.repository.PerfilRepository;
import br.com.e_commerce.demo.repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService{
    
    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired 
    private PerfilRepository perfilRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmailIgnoreCase(username).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado!"));
    }

    public DadosUsuario cadastrar(DadosCadastroUsuario dados) {
        var senhaCriptografada = encoder.encode(dados.senha());
        var perfil = perfilRepository.findByNome(PerfilEnum.CLIENTE);
        var usuario = new Usuario(dados, senhaCriptografada, perfil);
        repository.save(usuario);

        return new DadosUsuario(usuario.getNome(), usuario.getEmail());
    }

    public List<Usuario> listarTodos() {
        return repository.findAll();
    }
}
