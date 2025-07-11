package br.com.e_commerce.demo.domain.usuario;

public record DadosUsuario(
                String nome,
                String email) {
        public DadosUsuario(Usuario usuario) {
                this(usuario.getNome(), usuario.getEmail());
        }
}
