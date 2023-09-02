-- Inserir uma pessoa
INSERT INTO tb_pessoa (pessoa_id, nome, cpf, data_nascimento, tipo_sanguineo, contato, alergia, tipo_alergia, tipo_responsavel, cartao_nacional, cartao_plano_saude, ativo, doador, email, password)
VALUES ('9f360616-1811-4621-9b2c-6c7af609ff04', 'João da Silva', '123.456.789-01', '1990-01-15', 'A_POSITIVO', '48-12345-1234', 'false', NULL, 'true', '12345', NULL, 'true', 'false', 'joao@example.com', '$2a$10$PR8KTHSm.iIIOyykg.Nri.EjD9fRDoh5ARDVRLxYKPFiJiV2178P6');

-- Inserir um dependente
INSERT INTO tb_pessoa (pessoa_id, nome, cpf, data_nascimento, tipo_sanguineo, contato, alergia, tipo_alergia, tipo_responsavel, cartao_nacional, cartao_plano_saude, ativo, doador, email, password, responsavel_id)
VALUES ('d573dbe2-dfaf-41b1-8382-3bb9e686248f', 'Alice', '987.654.321-09', '2005-03-20', 'B_NEGATIVO', '48-12345-1234', 'true', 'Alergia a amendoim', 'false', NULL, NULL, 'true', 'false', 'maria@example.com', NULL, '9f360616-1811-4621-9b2c-6c7af609ff04');

INSERT INTO tb_pessoa (pessoa_id, nome, cpf, data_nascimento, tipo_sanguineo, contato, alergia, tipo_alergia, tipo_responsavel, cartao_nacional, cartao_plano_saude, ativo, doador, email, password, responsavel_id)
VALUES ('a4033f81-db39-4362-b137-78545815ff98', 'Lucas', '987.654.321-10', '2005-03-20', 'AB_POSITIVO', '49-12345-1234', 'false', NULL, 'false', NULL, NULL, 'true', 'false', 'lucas@example.com', NULL, '9f360616-1811-4621-9b2c-6c7af609ff04');
