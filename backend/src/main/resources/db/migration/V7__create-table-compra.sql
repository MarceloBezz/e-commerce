CREATE TABLE compra (
    id INT PRIMARY KEY,
    usuario_id INT,
    data_compra DATE,
    total DECIMAL(10,2),
    FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);