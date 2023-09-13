CREATE TABLE IF NOT EXISTS authors
(
    id        serial PRIMARY KEY,
    firstName varchar(50) NOT NULL,
    lastName  varchar(50) NOT NULL,
    age       int
);
CREATE TABLE IF NOT EXISTS books
(
    id      serial PRIMARY KEY,
    title   varchar(50) NOT NULL,
    pubYear int         NOT NULL
);

CREATE TABLE IF NOT EXISTS authorship
(
    author_id int,
    book_id   int,
    PRIMARY KEY (author_id, book_id),
    FOREIGN KEY (author_id) REFERENCES Authors (id),
    FOREIGN KEY (book_id) REFERENCES Books (id)
);
--**********************************************Developed by:***********************************************************
--******************************************* Sina Afzalsoltani ********************************************************
--===============================================Maktab 101=============================================================