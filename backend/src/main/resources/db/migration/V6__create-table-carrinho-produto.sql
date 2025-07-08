CREATE TABLE carrinho_produto (
    id INT PRIMARY KEY,
    carrinho_id INT,
    produto_id INT,
    quantidade INT NOT NULL,
    FOREIGN KEY (carrinho_id) REFERENCES carrinho(id),
    FOREIGN KEY (produto_id) REFERENCES produto(id)
);