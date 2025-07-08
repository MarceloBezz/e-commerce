CREATE TABLE perfis (
    id INT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL
);

INSERT INTO perfis (id, nome) 
VALUES (1, 'ADMINISTRADOR'), 
       (2, 'CLIENTE'), 
       (3, 'MODERADOR');
