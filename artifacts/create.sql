CREATE TABLE Area(
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    tipo_cultura VARCHAR(255) NOT NULL,
    ph FLOAT NOT NULL DEFAULT 7
);

CREATE TABLE Atividade(
    id INT PRIMARY KEY AUTO_INCREMENT, 
    fk_area INT NOT NULL,

    FOREIGN KEY (fk_area) REFERENCES Area(id)  
    ON DELETE SET NULL
);


CREATE TABLE Materiais(
    id INT PRIMARY KEY AUTO_INCREMENT,
);

CREATE TABLE Atividade_Materiais(
    fk_atividade INT NOT NULL,
    fk_materiais INT NOT NULL,
    qtd_estimada FLOAT NOT NULL DEFAULT 0
);