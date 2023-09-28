CREATE TABLE tb_acompanhamento (
    id UUID PRIMARY KEY,
    tipo_acompanhamento VARCHAR(255) NOT NULL,
    medicacao VARCHAR(255),
    status_evento VARCHAR(255),
    data_acompanhamento TIMESTAMP,
    intervalo_hora INTEGER,
    tipo_temporario_controlado VARCHAR(255),
    prescricao_medica TEXT,
    pessoa_id UUID,
    FOREIGN KEY (pessoa_id) REFERENCES tb_pessoa (pessoa_id)
);