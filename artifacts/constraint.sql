ALTER TABLE area ADD CONSTRAINT pk_area PRIMARY KEY (id);
ALTER TABLE talhao ADD CONSTRAINT pk_talhao PRIMARY KEY (id, area_id);
ALTER TABLE plano ADD CONSTRAINT pk_plano PRIMARY KEY (id, Especie_id);
ALTER TABLE atividade ADD CONSTRAINT pk_atividade PRIMARY KEY (id);
ALTER TABLE material ADD CONSTRAINT pk_material PRIMARY KEY (id);
ALTER TABLE atividade_has_material ADD CONSTRAINT pk_atividade_material PRIMARY KEY (material_id, atividade_id);

-- Fica com o grupo de Produção
-- ALTER TABLE canteiro_has_atividade ADD CONSTRAINT pk_canteiro_atividade PRIMARY KEY (canteiro_id, atividade_id);
-- ALTER TABLE canteiro ADD CONSTRAINT pk_canteiro PRIMARY KEY (id);

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
    FOREIGN KEY (talhao_id, talhao_area_id) REFERENCES talhao(id, area_id)
    ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE plano ADD CONSTRAINT fk_plano_especie
    FOREIGN KEY (Especie_id) REFERENCES especie(id)
    ON DELETE CASCADE ON UPDATE CASCADE;

-- material constraints
ALTER TABLE material ADD CONSTRAINT fk_material_associado
    FOREIGN KEY (associado_id) REFERENCES associado(id)
    ON DELETE CASCADE ON UPDATE CASCADE;

-- atividade_has_material constraints
ALTER TABLE atividade_has_material ADD CONSTRAINT fk_atividade_material_material
    FOREIGN KEY (material_id) REFERENCES material(id)
    ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE atividade_has_material ADD CONSTRAINT fk_atividade_material_atividade
    FOREIGN KEY (atividade_id) REFERENCES atividade(id)
    ON DELETE CASCADE ON UPDATE CASCADE;

-- Fica com o grupo de Produção
-- canteiro constraints
-- ALTER TABLE canteiro ADD CONSTRAINT fk_canteiro_plano
--     FOREIGN KEY (plano_id, plano_Especie_id) REFERENCES plano(id, Especie_id)
--     ON DELETE CASCADE ON UPDATE CASCADE;
-- 
-- -- canteiro_has_atividade constraints
-- ALTER TABLE canteiro_has_atividade ADD CONSTRAINT fk_canteiro_atividade_canteiro
--     FOREIGN KEY (canteiro_id) REFERENCES canteiro(id)
--     ON DELETE CASCADE ON UPDATE CASCADE;
-- 
-- ALTER TABLE canteiro_has_atividade ADD CONSTRAINT fk_canteiro_atividade_atividade
--     FOREIGN KEY (atividade_id) REFERENCES atividade(id)
--     ON DELETE CASCADE ON UPDATE CASCADE;