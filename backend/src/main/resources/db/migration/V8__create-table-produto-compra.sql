CREATE TABLE produto_compra (
    id INT PRIMARY KEY,
    compra_id INT,
    produto_id INT,
    quantidade INT NOT NULL,
    preco_unitario DECIMAL(10,2),
    FOREIGN KEY (compra_id) REFERENCES compra(id),
    FOREIGN KEY (produto_id) REFERENCES produto(id)
);