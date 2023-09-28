-- Inserir uma pessoa
INSERT INTO tb_pessoa (pessoa_id, nome, cpf, data_nascimento, contato, tipo_sanguineo, alergia, tipo_alergia, tipo_responsavel, cartao_nacional, cartao_plano_saude, tokenfcm, ativo, doador, email, password)
VALUES ('9f360616-1811-4621-9b2c-6c7af609ff04', 'João da Silva', '123.456.789-01', '1990-01-15','(48)12345-1234', 'A_POSITIVO','false', NULL, 'true', '1111 1111 1111', '12344522', NULL, 'true', 'false','joao@example.com', '$2a$10$PR8KTHSm.iIIOyykg.Nri.EjD9fRDoh5ARDVRLxYKPFiJiV2178P6');

-- Inserir um dependente
INSERT INTO tb_pessoa (pessoa_id, nome, cpf, data_nascimento, contato, tipo_sanguineo, alergia, tipo_alergia, tipo_responsavel, cartao_nacional, cartao_plano_saude, tokenfcm, ativo, doador, email, password, responsavel_id)
VALUES ('d573dbe2-dfaf-41b1-8382-3bb9e686248f', 'Alice', '987.654.321-09', '2005-03-20', '(48)12345-1234','B_NEGATIVO', TRUE, 'Alergia a amendoim', FALSE, '1111 2222 444', '98767762', NULL, TRUE, TRUE,'maria@example.com', NULL,'9f360616-1811-4621-9b2c-6c7af609ff04');

INSERT INTO tb_pessoa (pessoa_id, nome, cpf, data_nascimento, contato, tipo_sanguineo, alergia, tipo_alergia, tipo_responsavel, cartao_nacional, cartao_plano_saude, tokenfcm, ativo, doador, email, password, responsavel_id)
VALUES ('a4033f81-db39-4362-b137-78545815ff98', 'Lucas', '987.654.321-10', '2005-03-20', '(49)-12345-1234', 'AB_POSITIVO',FALSE, NULL, 'false', NULL, NULL, NULL, TRUE, TRUE,'lucas@example.com', NULL, '9f360616-1811-4621-9b2c-6c7af609ff04');
