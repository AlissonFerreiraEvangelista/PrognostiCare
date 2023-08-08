CREATE TABLE tb_user(
    user_id uuid PRIMARY KEY,
    email character varying(255) UNIQUE NOT NULL,
    password character varying(255) NOT NULL,
    name character varying(255) NOT NULL	
)