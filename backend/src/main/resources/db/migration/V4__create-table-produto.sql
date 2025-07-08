CREATE TABLE produto (
    id INT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    preco DECIMAL(10,2) NOT NULL,
    quantidade INT NOT NULL,
    anunciante INT,
    FOREIGN KEY (anunciante) REFERENCES usuario(id)
);