CREATE TABLE IF NOT EXISTS authors (
    id serial PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    sex CHAR(1) CHECK (sex IN ('M', 'F'))

);

INSERT INTO authors (id, name, sex)
VALUES
    ('1', 'Kishimito', 'M'),
    ('2', ' Hiroyuki', 'M'),
    ('3', 'James Clear', 'M');