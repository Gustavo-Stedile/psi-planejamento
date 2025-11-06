CREATE TABLE area (
    id SERIAL,
    associado_id BIGINT NOT NULL,
    nome VARCHAR(255),
    area_total FLOAT,
    area_utilizada FLOAT,
    ph FLOAT,
    area_m2 FLOAT
);

CREATE TABLE talhao (
    id SERIAL,
    Area_id INTEGER NOT NULL,
    nome VARCHAR(255),
    area_talhao FLOAT,
    observacoes TEXT,
    status_2 VARCHAR(50)
);

CREATE TABLE plano (
    id SERIAL,
    Especie_id INTEGER NOT NULL,
    Talhao_Area_id INTEGER NOT NULL,
    Talhao_id INTEGER NOT NULL,
    nome_plano VARCHAR(255),
    descricao TEXT,
    data_inicio DATE,
    data_fim DATE,
    observacoes TEXT,
    area_cultivo FLOAT
);

CREATE TABLE canteiro (
    id SERIAL,
    Plano_Especie_id INTEGER NOT NULL,
    Plano_id INTEGER NOT NULL,
    nome VARCHAR(255),
    area_canteriro_m2 FLOAT,
    observacoes TEXT,
    kg_gerados FLOAT
);

CREATE TABLE atividade (
    id SERIAL,
    nome_atividade VARCHAR(255),
    descricao TEXT,
    observacoes TEXT,
    status_2 VARCHAR(50)
);

CREATE TABLE canteiro_has_Atividade (
    Canteiro_id INTEGER NOT NULL,
    Atividade_id INTEGER NOT NULL,
    tempo_gasto_horas FLOAT,
    data_atividade DATE
);

CREATE TABLE material (
    id SERIAL,
    associado_id BIGINT NOT NULL,
    nome VARCHAR(255),
    quantidade FLOAT,
    unidade_medida VARCHAR(50)
);

CREATE TABLE atividade_has_Material (
    Material_id INTEGER NOT NULL,
    Atividade_id INTEGER NOT NULL,
    quantidade_utilizada FLOAT
);