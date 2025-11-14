-- Inserts para tabela area
INSERT INTO area (associado_id, nome, area_total, area_utilizada, ph, area_m2) VALUES
(1, 'Fazenda São João', 50.5, 35.2, 6.2, 50500),
(1, 'Sítio Esperança', 25.0, 18.7, 5.8, 25000),
(2, 'Chácara Boa Vista', 30.0, 22.3, 6.5, 30000),
(2, 'Fazenda Verde Vida', 45.2, 30.1, 6.0, 45200),
(3, 'Sítio Recanto Feliz', 15.8, 12.5, 5.9, 15800);

-- Inserts para tabela talhao
INSERT INTO talhao (area_id, nome, area_talhao, observacoes, status) VALUES
(1, 'Talhão A1', 5.2, 'Solo argiloso, boa drenagem', 'Ativo'),
(1, 'Talhão A2', 4.8, 'Próximo ao rio, umidade alta', 'Ativo'),
(2, 'Talhão B1', 3.5, 'Inclinação moderada', 'Inativo'),
(3, 'Talhão C1', 6.1, 'Solo arenoso, precisa adubação', 'Ativo'),
(4, 'Talhão D1', 5.7, 'Área plana, irrigação por gotejamento', 'Ativo');

-- Inserts para tabela plano
INSERT INTO plano (especie_id, talhao_area_id, talhao_id, nome_plano, descricao, data_inicio, data_fim, observacoes, area_cultivo) VALUES
(1, 1, 1, 'Plano Alface Primavera', 'Cultivo de alface para primavera', '2024-09-01', '2024-11-15', 'Usar adubo orgânico', 5.0),
(2, 1, 2, 'Plano Rúcula Verão', 'Cultivo de rúcula para verão', '2024-10-01', '2024-12-10', 'Controlar irrigação', 4.5),
(3, 3, 4, 'Plano Cenoura Outono', 'Cultivo de cenoura outonal', '2024-08-15', '2024-10-30', 'Solo precisa correção', 6.0),
(4, 4, 5, 'Plano Beterraba Inverno', 'Cultivo de beterraba de inverno', '2024-07-01', '2024-09-20', 'Proteger de geadas', 5.5),
(5, 2, 3, 'Plano Espinafre Ano Todo', 'Cultivo contínuo de espinafre', '2024-09-10', '2025-03-10', 'Colheita escalonada', 3.2);

-- Inserts para tabela Canteiro
INSERT INTO Canteiro (plano_especie_id, plano_id, nome, area_canteriro_m2, observacoes, kg_gerados) VALUES
(1, 1, 'Canteiro Alface 1', 120, 'Canteiro sombreado', 280.5),
(1, 1, 'Canteiro Alface 2', 100, 'Canteiro ensolarado', 240.2),
(2, 2, 'Canteiro Rúcula 1', 80, 'Irrigação automática', 132.8),
(3, 3, 'Canteiro Cenoura 1', 150, 'Solo profundo', 450.0),
(4, 4, 'Canteiro Beterraba 1', 130, 'Protegido do vento', 345.6);

-- Inserts para tabela atividade
INSERT INTO atividade (nome_atividade, descricao, observacoes, status) VALUES
('Preparação do Solo', 'Revolvimento e adubação do solo', 'Usar trator para aração', 'Concluída'),
('Plantio', 'Plantio das mudas no canteiro', 'Espaçamento de 30cm', 'Concluída'),
('Irrigação', 'Sistema de irrigação por gotejamento', 'Verificar vazamentos', 'Em Andamento'),
('Adubação', 'Aplicação de adubo orgânico', 'Usar esterco curtido', 'Pendente'),
('Colheita', 'Colheita manual dos produtos', 'Realizar pela manhã', 'Pendente');

-- Inserts para tabela atividade_canteiro
INSERT INTO atividade_canteiro (canteiro_id, atividade_id, tempo_gasto_horas, data_atividade) VALUES
(1, 1, 8.5, '2024-09-01'),
(1, 2, 6.0, '2024-09-02'),
(2, 1, 7.0, '2024-09-01'),
(3, 3, 12.5, '2024-09-05'),
(4, 2, 5.5, '2024-08-20');

-- Inserts para tabela material
INSERT INTO material (associado_id, nome, quantidade, unidade_medida) VALUES
(1, 'Adubo Orgânico', 500.0, 'kg'),
(1, 'Sementes de Alface', 2.5, 'kg'),
(1, 'Mangueira Irrigação', 150.0, 'metros'),
(2, 'Fertilizante NPK', 100.0, 'kg'),
(3, 'Calcário Dolomítico', 300.0, 'kg');

-- Inserts para tabela material_atividade
INSERT INTO material_atividade (material_id, atividade_id, quantidade_utilizada) VALUES
(1, 1, 50.0),
(2, 2, 0.5),
(3, 3, 25.0),
(4, 4, 15.0),
(1, 4, 30.0);

