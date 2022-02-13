DROP TABLE IF EXISTS BOOK;
CREATE TABLE BOOK(ID BIGINT IDENTITY PRIMARY KEY,
TITLE VARCHAR(255) NOT NULL,
AUTHOR_ID BIGINT,
GENRE_ID BIGINT);

DROP TABLE IF EXISTS GENRE;
CREATE TABLE GENRE(ID BIGINT IDENTITY PRIMARY KEY, NAME VARCHAR(255) NOT NULL);

DROP TABLE IF EXISTS AUTHOR;
CREATE TABLE AUTHOR(ID BIGINT IDENTITY PRIMARY KEY, NAME VARCHAR(255) NOT NULL);

DROP TABLE IF EXISTS COMMENT;
CREATE TABLE COMMENT(ID BIGINT IDENTITY PRIMARY KEY, TEXT VARCHAR(255) NOT NULL, BOOK_ID BIGINT);

ALTER TABLE BOOK
    ADD FOREIGN KEY (GENRE_ID)
    REFERENCES GENRE(ID) ON DELETE CASCADE;

ALTER TABLE BOOK
    ADD FOREIGN KEY (AUTHOR_ID)
    REFERENCES AUTHOR(ID) ON DELETE CASCADE;

ALTER TABLE GENRE
    ADD CONSTRAINT GENRE_NAME_UNIQUE
    UNIQUE (NAME);

ALTER TABLE COMMENT
    ADD FOREIGN KEY (BOOK_ID)
    REFERENCES BOOK(ID) ON DELETE CASCADE;


