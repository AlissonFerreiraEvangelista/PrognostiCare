CREATE TABLE tb_agenda (
    id UUID PRIMARY KEY,
    data_agenda TIMESTAMP,
    local VARCHAR(255),
    intervalo_data INTEGER,
    descricao TEXT,
    status_evento VARCHAR(255),
    observacao TEXT,
    especialista VARCHAR(255),
    tipo_exame VARCHAR(255) NOT NULL,
    pessoa_id UUID,
    notificacao BOOLEAN,
    FOREIGN KEY (pessoa_id) REFERENCES tb_pessoa (pessoa_id)
);