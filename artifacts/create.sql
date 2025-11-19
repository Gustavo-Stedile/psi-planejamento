CREATE TABLE area (
    id SERIAL,
    associado_id BIGINT NOT NULL,
    nome VARCHAR(255),
    area_total FLOAT,
    area_utilizada FLOAT,
    ph FLOAT,
    area_m2 FLOAT,
    ativo BOOLEAN
);

CREATE TABLE talhao (
    id SERIAL,
    area_id INTEGER NOT NULL,
    nome VARCHAR(255),
    area_talhao FLOAT,
    observacoes TEXT,
    status VARCHAR(50),
    ativo BOOLEAN
);

CREATE TABLE plano (
    id SERIAL,
    especie_id INTEGER NOT NULL,
    talhao_id INTEGER NOT NULL,
    nome_plano VARCHAR(255),
    descricao TEXT,
    data_inicio DATE,
    data_fim DATE,
    observacoes TEXT,
    area_cultivo FLOAT,
    ativo BOOLEAN
);

CREATE TABLE atividade (
    id SERIAL,
    nome_atividade VARCHAR(255),
    descricao TEXT,
    observacoes TEXT,
    status VARCHAR(50),
    ativo BOOLEAN
);

CREATE TABLE material (
    id SERIAL,
    associado_id BIGINT NOT NULL,
    nome VARCHAR(255),
    quantidade FLOAT,
    unidade_medida VARCHAR(50),
    ativo BOOLEAN
);

CREATE TABLE material_atividade ( --> atividade_has_material
    material_id INTEGER NOT NULL,
    atividade_id INTEGER NOT NULL,
    quantidade_utilizada FLOAT,
    ativo BOOLEAN
);

-- Fica com o grupo de Produção
-- CREATE TABLE canteiro (
--     id SERIAL,
--     plano_id INTEGER NOT NULL,
--     nome VARCHAR(255),
--     area_canteiro_m2 FLOAT,
--     observacoes TEXT,
--     kg_gerados FLOAT,
--     ativo BOOLEAN
-- );

-- CREATE TABLE atividade_canteiro ( --> canteiro_has_atividade
--     Canteiro_id INTEGER NOT NULL,
--     atividade_id INTEGER NOT NULL,
--     tempo_gasto_horas FLOAT,
--     data_atividade DATE,
--     ativo BOOLEAN
-- );
