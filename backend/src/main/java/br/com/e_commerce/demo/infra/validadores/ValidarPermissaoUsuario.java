package br.com.e_commerce.demo.infra.validadores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.e_commerce.demo.domain.usuario.Usuario;
import br.com.e_commerce.demo.repository.UsuarioRepository;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class ValidarPermissaoUsuario {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    public boolean validarPermissaoUsuario(Usuario usuarioLogado, Long id, String role) {
        var usuarioBD = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        if (!usuarioBD.getId().equals(usuarioLogado.getId())) {
            boolean temPermissao = usuarioLogado
                    .getAuthorities()
                    .stream()
                    .anyMatch(x -> x.getAuthority().equals("ROLE_" + role));

            if (!temPermissao) {
                return false;
            }
        }
        return true;
    }
}
