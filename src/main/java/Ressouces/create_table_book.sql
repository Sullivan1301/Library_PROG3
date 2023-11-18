CREATE TABLE IF NOT EXISTS book (
    id serial PRIMARY KEY,
    nameBook VARCHAR(255) NOT NULL,
    pageNumbers int NOT NULL,
    topic VARCHAR(20) DEFAULT 'OTHER' CHECK (topic IN('COMEDY','ROMANCE','OTHER' )),
    releaseDate DATE,
    author VARCHAR (255),
    status VARCHAR(50),

    CONSTRAINT fkAuthor Foreign Key (author) REFERENCES author(name)
);

INSERT INTO book VALUES ('1', 'Naruto', 90, 'COMEDY', '2023-11-16', 1, 'AVAILABLE'),
    ('2', 'Kanojo no Kanojo', 120, 'ROMANCE', '2023-11-14', 2, 'BORROWED'),
    ('3', 'Atomic Habits', 200, 'OTHER', '2023-11-17', 3, 'AVAILABLE');