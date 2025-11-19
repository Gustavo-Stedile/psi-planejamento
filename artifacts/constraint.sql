ALTER TABLE area ADD CONSTRAINT pk_area PRIMARY KEY (id);
ALTER TABLE talhao ADD CONSTRAINT pk_talhao PRIMARY KEY (id, area_id);
ALTER TABLE plano ADD CONSTRAINT pk_plano PRIMARY KEY (id, especie_id);
ALTER TABLE atividade ADD CONSTRAINT pk_atividade PRIMARY KEY (id);
ALTER TABLE material ADD CONSTRAINT pk_material PRIMARY KEY (id);
ALTER TABLE material_atividade ADD CONSTRAINT pk_atividade_material PRIMARY KEY (material_id, atividade_id);

-- DEFAULT VALUES

ALTER TABLE area ALTER COLUMN ativo SET DEFAULT true;
ALTER TABLE talhao ALTER COLUMN ativo SET DEFAULT true;
ALTER TABLE plano ALTER COLUMN ativo SET DEFAULT true;
ALTER TABLE atividade ALTER COLUMN ativo SET DEFAULT true;
ALTER TABLE atividade_canteiro ALTER COLUMN ativo SET DEFAULT true;
ALTER TABLE material ALTER COLUMN ativo SET DEFAULT true;
ALTER TABLE material_atividade ALTER COLUMN ativo SET DEFAULT true;

-- FOREIGN KEY CONSTRAINTS

-- area constraints
ALTER TABLE area ADD CONSTRAINT fk_area_associado
    FOREIGN KEY (associado_id) REFERENCES associado(id)
    ON DELETE CASCADE ON UPDATE CASCADE;

-- talhao constraints
ALTER TABLE talhao ADD CONSTRAINT fk_talhao_area
    FOREIGN KEY (area_id) REFERENCES area(id)
    ON DELETE CASCADE ON UPDATE CASCADE;

-- plano constraints
ALTER TABLE plano ADD CONSTRAINT fk_plano_talhao
    FOREIGN KEY (talhao_id) REFERENCES talhao(id)
    ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE plano ADD CONSTRAINT fk_plano_especie
    FOREIGN KEY (especie_id) REFERENCES especie(id)
    ON DELETE CASCADE ON UPDATE CASCADE;

-- material constraints
ALTER TABLE material ADD CONSTRAINT fk_material_associado
    FOREIGN KEY (associado_id) REFERENCES associado(id)
    ON DELETE CASCADE ON UPDATE CASCADE;

-- material_atividade constraints
ALTER TABLE material_atividade ADD CONSTRAINT fk_atividade_material_material
    FOREIGN KEY (material_id) REFERENCES material(id)
    ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE material_atividade ADD CONSTRAINT fk_atividade_material_atividade
    FOREIGN KEY (atividade_id) REFERENCES atividade(id)
    ON DELETE CASCADE ON UPDATE CASCADE;

-- Fica para a produção
-- ALTER TABLE canteiro ADD CONSTRAINT pk_canteiro PRIMARY KEY (id);
-- ALTER TABLE atividade_canteiro ADD CONSTRAINT pk_canteiro_atividade PRIMARY KEY (canteiro_id, atividade_id);

-- ALTER TABLE canteiro ALTER COLUMN ativo SET DEFAULT true;
-- canteiro constraints
-- ALTER TABLE canteiro ADD CONSTRAINT fk_canteiro_plano
--     FOREIGN KEY (plano_id) REFERENCES plano(id)
--     ON DELETE CASCADE ON UPDATE CASCADE;

-- atividade_canteiro constraints
-- ALTER TABLE atividade_canteiro ADD CONSTRAINT fk_canteiro_atividade_canteiro
--     FOREIGN KEY (canteiro_id) REFERENCES canteiro(id)
--     ON DELETE CASCADE ON UPDATE CASCADE;

-- ALTER TABLE atividade_canteiro ADD CONSTRAINT fk_canteiro_atividade_atividade
--     FOREIGN KEY (atividade_id) REFERENCES atividade(id)
--     ON DELETE CASCADE ON UPDATE CASCADE;
