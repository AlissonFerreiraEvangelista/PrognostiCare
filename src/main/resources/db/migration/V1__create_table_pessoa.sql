CREATE TABLE tb_pessoa(
    pessoa_id UUID PRIMARY KEY,
    nome VARCHAR(255),
    cpf VARCHAR(14) NOT NULL UNIQUE,
    data_nascimento DATE,
    tipo_sanguineo VARCHAR(255),
    contato VARCHAR(255),
    alergia BOOLEAN,
    tipo_alergia VARCHAR(255),
    tipo_responsavel BOOLEAN,
    cartao_nacional VARCHAR(255),
    cartao_plano_saude VARCHAR(255),
    ativo BOOLEAN,
    doador BOOLEAN,
    email VARCHAR(255) UNIQUE,
    password VARCHAR(255),
    responsavel_id UUID,
    FOREIGN KEY (responsavel_id) REFERENCES tb_pessoa(pessoa_id)
);