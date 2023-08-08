
CREATE TABLE tb_users_roles
(
    user_id uuid NOT NULL,
    role_id uuid NOT NULL,
    CONSTRAINT fk_tb_role FOREIGN KEY (role_id)
        REFERENCES tb_role (role_id),
    CONSTRAINT fk_tb_user FOREIGN KEY (user_id)
        REFERENCES tb_user (user_id)
)
