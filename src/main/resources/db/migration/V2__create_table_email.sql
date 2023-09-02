CREATE TABLE tb_email(
    id SERIAL PRIMARY KEY,
    owner_ref VARCHAR(255),
    email_from VARCHAR(255),
    email_to VARCHAR(255),
    subject VARCHAR(255),
    text TEXT,
    send_date_email TIMESTAMP,
    status_email VARCHAR(255)
);