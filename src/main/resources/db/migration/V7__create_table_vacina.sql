-- Criação da tabela
CREATE TABLE tb_vacina (
    id SERIAL PRIMARY KEY,
    tipo_vacina VARCHAR(255),
    vacina VARCHAR(255),
    protecao_contra VARCHAR(255),
    composicao VARCHAR(255),
    esquema_basico VARCHAR(255),
    reforco VARCHAR(255),
    idade_recomendada VARCHAR(255),
    intervalo_recomendado VARCHAR(255),
    intervalo_minimo VARCHAR(255)
);

-- Inserção de dados
INSERT INTO tb_vacina (tipo_vacina, vacina, protecao_contra, composicao, esquema_basico, reforco, idade_recomendada, intervalo_recomendado, intervalo_minimo)
VALUES
    ('CRIANCA','BCG', 'Formas graves de tuberculose (meníngea e miliar)', 'Bactéria viva atenuada', 'Dose única', NULL,'Ao nascer', NULL, NULL),
    ('CRIANCA','Hepatite B (HB - recombinante)', 'Hepatite B', 'Antígeno recombinante de superfície do vírus purificado', 'Dose única', NULL,'Ao nascer', NULL, NULL),
    ('CRIANCA','Poliomielite 1, 2 e 3 (VIP - inativada)','Poliomielite Vírus inativado','Polissacarídeo capsular de 10 sorotipos de pneumococos','3 doses 2 reforços com a vacina VOP','1ª dose: 2 meses, 2ª dose: 4 meses, 3ª dose: 6 meses','60 dias, 30 dias',NULL, NULL),
    ('CRIANCA','Poliomielite 1 e 3 (VOPb - atenuada)','Poliomielite', 'Vírus vivo atenuado',NULL,'2 doses de reforço','15 meses e 4 anos',NULL,'1º reforço: 6 meses após 3ª dose da VIP, 2º reforço: 6 meses após 1º reforço'),
    ('CRIANCA','Rotavírus humano G1P[8] (ROTA)','Diarreia por Rotavírus','Vírus vivo atenuado','2 doses',NULL,'1ª dose: 2 meses, 2ª dose: 4 meses','60 dias','30 dias'),
    ('CRIANCA','(DTP/HB/Hib) (Penta)','Difteria, Tétano,Coqueluche, Haemophilus influenzae  B e Hepatite B','Toxoides diftérico e tetânico purificados e bactéria da coqueluche inativada. Oligossacarídeos conjugados do HiB, antígeno de superfície de HB.','3 doses','2 reforços com a vacina DTP','1ª dose: 2 meses 2ª dose: 4 meses 3ª dose: 6 meses','60 dias','30 dias'),
    ('CRIANCA','Pneumocócica 10 - valente (VPC 10 - conjugada)','Pneumonias, Meningites, Otites, Sinusites pelos sorotipos que compõem a vacina','Polissacarídeo capsular de 10 sorotipos de pneumococos','2 doses','Reforço','1ª dose: 2 meses 2ª dose: 4 meses Reforço: 12 meses','60 dias','30 dias  entre a 1ª dose e 2ª dose 60 dias entre a 2ª dose e oreforço'),
    ('CRIANCA','Meningocócica C (conjugada)','Meningite meningocócica tipo  C','Polissacarídeos capsulares purificados da Neisseria meningitidis do sorogrupo C','2 doses','Reforço','1ª dose:3 meses 2ª dose: 5 meses Reforço: 12 meses','60 dias','30 dias  entre a 1ª dose e 2ª dose 60 dias entre a 2ª dose e o reforço'),
    ('CRIANCA','Febre Amarela (VFA - atenuada)','Febre Amarela','Vírus vivo atenuado','Uma dose','Reforço','Dose: 9 meses Reforço: 4 anos de idade',NULL,'30 dias'),
    ('CRIANCA','Sarampo, caxumba, rubéola (SCR - atenuada) (Tríplice viral)','Sarampo, Caxumba e Rubéola','Vírus vivo atenuado','2 doses',NULL,'12 meses',NULL,'30 dias'),
    ('CRIANCA','Sarampo, caxumba, rubéola e varicela (SCRV – atenuada) (Tetraviral)','Sarampo, Caxumba,Rubéola e Varicela','Vírus vivo atenuado','Uma dose (2ª dose da tríplice viral e 1ª de varicela)',NULL,'15 meses',NULl,NULL),
    ('CRIANCA','Hepatite A (HA - inativada)','Hepatite A','Vírus inativado','Uma dose',NULL,'15 meses',NULL,NULL),
    ('CRIANCA','Difteria, Tétano e Pertussis (DTP)','Difteria, Tétano eCoqueluche','Toxoides diftérico e tetânico purificados e bactéria da coqueluche (inativada)','3 doses (Considerar doses anteriores)','2 reforços','1º reforço: 15 meses 2º reforço: 4 anos de idade','1º reforço: 9 meses após 3ª dose 2º reforço: 3 anos após 1º reforço',NULL),
    ('CRIANCA','Difteria e Tétano (dT)','Difteria e Tétano','Toxoides diftérico e tetânico purificados','3 doses (Considerar doses anteriores com Penta e DTP)','A cada 10 anos. Em caso de ferimentos graves, deve-se reduzir este intervalo para 5 anos','A partir dos 7 anos','60 dias',NULL),
    ('CRIANCA','Papilomavírus humano 6, 11, 16 e 18 (HPV4 - recombinante)','Papilomavírus  Humano 6, 11, 16 e 18 (recombinante)','Papilomavírus humano 6, 11, 16 e 18 (HPV4 –recombinante)','2 doses',NULL,'09 a 10 anos (Para meninas e meninos)','2ª dose: 6 meses após 1ª dose',NULL),
    ('CRIANCA','Pneumocócica 23-valente (VPP 23 - (polissacarídica)','Meningites bacterianas, Pneumonias, Sinusite e outros.','Polissacarídeo capsular de 23 sorotipos de pneumococos','2 doses','Uma dose a depender da situação vacinal  anterior com a PCV 10','A partir de 5 anos para os povos indígenas. A 2ª dose deve ser feita 5 anos após a 1ª dose','5 anos',NULL),
    ('CRIANCA','Varicela (VZ – atenuada)','Varicela (Catapora)','Vírus vivo atenuado','Uma dose (Corresponde a 2ª dose da varicela)',NULL,'4 anos',NULL,NULL),
    ('ADOLESCENTE','Hepatite B (HB - recombinante)','Hepatite B','Antígeno recombinante de superfície do vírus purificado','Iniciar ou completar 3 doses, de acordo com situação vacinal',NULL,NULL,'2ª dose: 1 mês após 1ª dose.3ª dose: 6 meses após 1ª dose.','2ª dose: 1 mês após 1ª dose. 3ª dose: 4 meses após 1ª dose.'),
    ('ADOLESCENTE','Difteria e Tétano (dT)','Difteria e Tétano','Toxoides diftérico e tetânico purificados','Iniciar ou completar 3 doses, de acordo com situação vacinal','A cada 10 anos. Em caso de ferimentos graves, deve-se reduzir este intervalo para 5 anos',NULL,'60 dias','30 dias'),
    ('ADOLESCENTE','Febre Amarela (VFA - atenuada)','Febre Amarela','Vírus vivo atenuado','Dose única','Reforço,  caso a pessoa tenha recebido uma dose da vacina antes de completar 5 anos de idade',NULL,NULL,NULL),
    ('ADOLESCENTE','Sarampo, caxumba, rubéola (SCR - atenuada) (Tríplice viral)','Sarampo, Caxumba e Rubéola','Vírus vivo atenuado','Iniciar ou completar 2 doses, de acordo com situação vacinal',NULL,NULL,NULL,'30 dias'),
    ('ADOLESCENTE','Papilomavírus humano 6, 11, 16 e 18 (HPV4 -recombinante)','Papilomavírus Humano 6, 11, 16 e 18 (recombinante)','Papilomavírus humano 6, 11, 16 e 18 (HPV4 –recombinante)','Iniciar ou completar 2 doses, de acordo com situação vacinal',NULL,'11 a 14 anos (Para meninas e meninos)','2ª dose: 6 meses após 1ª dose','2ª dose: 6 meses após 1ª dose'),
    ('ADOLESCENTE','Pneumocócica 23-valente (VPP 23 - (polissacarídica)','Meningites bacterianas, Pneumonias, Sinusite e outros.','Polissacarídeo capsular de 23 sorotipos de pneumococos','Uma dose','Uma dose a depender da situação vacinal anterior com a PCV 10','A partir de 5 anos para ospovos indígenas. A 2ª dose deve ser feita 5 anos após a 1ª dose','5 anos','3 anos'),
    ('ADOLESCENTE','Meningocócica ACWY (MenACWY- conjugada)','Meningite meningocócica sorogrupos A, C, W e Y','Polissacarídeos capsulares purificados da Neisseria meningitidis dos sorogrupos A, C, W e Y','Uma dose',NULL,'11 a 14 anos',NULL,NULL),
    ('GESTANTE','Hepatite B (HB - recombinante)','Hepatite B','Antígeno recombinante de superfície do vírus purificado','Iniciar ou completar 3 doses, de acordo com histórico vacinal',NULL,NULL,'2ª dose: 1 mês após 1ª dose. 3ª dose: 6 meses após 1ª dose.','2ª dose: 1 mês após 1ª dose. 3ª dose: 4 meses após 1ª dose.'),
    ('GESTANTE','Difteria e Tétano (dT)','Difteria e Tétano','Toxoides diftérico e tetânico purificados','Iniciar ou completar 3 doses, de acordo com histórico vacinal','A cada 10 anos. Ferimentos graves, deve-se reduzir este intervalo para 5 anos',NULL,'60 dias','30 dias'),
    ('GESTANTE','Difteria, Tétano, Pertussis (dTpa - acelular)','Difteria, Tétano e Coqueluche','Toxoides diftérico e tetânico purificados e componentes acelulares da coqueluche inativada','Uma dose','Uma dose a cada gestação','Gestantes a partir da 20ª semana de gravidez e puérperas até 45 dias','60 dias após dT','30 dias após dT'),
    ('ADULTO_E_IDOSO','Hepatite B (HB - recombinante)','Hepatite B','Antígeno recombinante de superfície do vírus purificado','Iniciar ou completar 3 doses,de acordo com histórico vacinal',NULL,NULL,'2ª dose: 1 mês após 1ª dose. 3ª dose: 6 meses após 1ª dose.','2ª dose: 1 mês após 1ª dose. 3ª dose: 4 meses após 1ª dose.'),
    ('ADULTO_E_IDOSO','Difteria e Tétano (dT)','Difteria e Tétano','Toxoides diftérico e tetânico purificados','Iniciar ou completar 3 doses,de acordo com histórico vacinal','A cada 10 anos. Em caso de ferimentos graves, deve-se reduzir este intervalo para 5 anos.',NULL,'60 dias','30 dias'),
    ('ADULTO_E_IDOSO','Febre Amarela (VFA - atenuada)','Febre Amarela','Vírus vivo atenuado','Dose única','Reforço, caso a pessoa tenha recebido uma dose da vacina antes de completar 5 anos de idade',NULL,NULL,NULL),
    ('ADULTO_E_IDOSO','Sarampo, caxumba, rubéola (SCR - atenuada)(Tríplice viral)','Sarampo, Caxumba e Rubéola','Vírus vivo atenuado','2 doses (20 a 29 anos) Uma dose (30 a 59 anos) (verificar situação vacinal anterior)',NULL,NULL,NULL,'30 dias (Se duas doses)'),
    ('ADULTO_E_IDOSO','Pneumocócica 23-valente (VPP 23 - (polissacarídica)*','Meningites bacterianas, Pneumonias,Sinusite e outros.','Polissacarídeo capsular de 23 sorotipos de pneumococos','2 doses (A 2ª dose deve ser feita 5 anos após a 1ª dose)',NULL,'60 anos','5 anos','3 anos'),
    ('ADULTO_E_IDOSO','Varicela (VZ – atenuada)**','Varicela (catapora)','Vírus vivo atenuado','Uma ou duas doses a depender do fabricante',NULL,'A partir dos 18 anos','30 dias (quando houver indicação de duas doses)',NULL),
    ('ADULTO_E_IDOSO','Difteria, Tétano, Pertussis (dTpa - acelular)**','Difteria, Tétano e Coqueluche','Toxoides diftérico e tetânico purificados e componentes acelulares da coqueluche inativada','Uma dose','Uma dose a cada 10 anos','A partir dos 18 anos','10 anos','5 anos em caso de ferimentos graves')
    

