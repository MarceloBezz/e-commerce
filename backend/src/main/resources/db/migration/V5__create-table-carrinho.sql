CREATE TABLE carrinho (
    id INT PRIMARY KEY,
    usuario_id INT,
    valor DECIMAL(10,2),
    FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);