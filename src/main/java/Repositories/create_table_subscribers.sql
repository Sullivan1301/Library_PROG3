-- create_table_subscribers.sql
CREATE TABLE IF NOT EXISTS subscribers (
    id serial PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    sex CHAR(1) CHECK (sex IN ('M', 'F')),
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

INSERT INTO subscribers (id, name, sex, username, password)
VALUES 
    ('1', 'Sullivan Joro', 'M', 'SullivanJ', 'un23456'),
    ('2', 'Rojosoa Nayah', 'F', 'NayahRojo', 'MotDePasse'),
    ('3', 'Tahina', 'M', 'Taiihns', 'un2un2');
