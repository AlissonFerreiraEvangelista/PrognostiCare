
INSERT INTO tb_agenda (id, data_agenda, local, intervalo_data, descricao, status_evento, observacao, especialista, tipo_exame, pessoa_id, notificacao)
VALUES (
    'e5e0b0e0-7c84-4e8d-9d16-0f318d5f2b12',
    '2023-09-22 10:00:00 AM',
    'Hospital A',
    3,
    'Regular checkup',
    'ABERTO',
    'No special instructions',
    'CARDIOLOGIA',
    'CONSULTA',
    '9f360616-1811-4621-9b2c-6c7af609ff04',
    TRUE
);


INSERT INTO tb_acompanhamento (id, tipo_acompanhamento, medicacao, status_evento, data_acompanhamento, intervalo_hora, tipo_temporario_controlado, prescricao_medica, pessoa_id)
VALUES (
    'f7eb6757-4db1-4e31-9a5f-d53e50c2b4b3',
    'MEDICACAO',
    'Aspirin',
    'FINALIZADO',
    '2023-09-22 10:30:00 AM',
    4,
    'MEDICACAO',
    'Take with water',
    'd573dbe2-dfaf-41b1-8382-3bb9e686248f'
);
