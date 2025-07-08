CREATE TABLE carrinhos_produtos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    carrinho_id INT,
    produto_id INT,
    quantidade INT NOT NULL,
    FOREIGN KEY (carrinho_id) REFERENCES carrinhos(id),
    FOREIGN KEY (produto_id) REFERENCES produtos(id)
);