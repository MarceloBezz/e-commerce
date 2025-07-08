CREATE TABLE carrinhos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT,
    valor DECIMAL(10,2),
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);