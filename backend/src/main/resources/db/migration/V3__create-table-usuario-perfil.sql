CREATE TABLE usuario_perfil (
    id INT PRIMARY KEY,
    usuario_id INT,
    perfil_id INT,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id),
    FOREIGN KEY (perfil_id) REFERENCES perfil(id)
);