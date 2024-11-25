--liquibase formatted sql
--changeset ism:create-new-users

INSERT INTO users (name, email, password, aboutme) VALUES ('Ivan', 'ivan@1.1', 'password', 'good boy');
