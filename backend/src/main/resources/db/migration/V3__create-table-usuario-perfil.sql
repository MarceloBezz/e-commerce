CREATE TABLE usuarios_perfis (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT,
    perfil_id INT,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    FOREIGN KEY (perfil_id) REFERENCES perfis(id)
);