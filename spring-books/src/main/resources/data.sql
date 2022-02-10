insert into GENRE (id, NAME) values (1, 'NOVEL');
insert into GENRE (id, NAME) values (2, 'COMEDY');

insert into AUTHOR (id, NAME) VALUES (1, 'FRANZ KAFKA');
insert into AUTHOR (id, NAME) VALUES (2, 'NIKOLAY GOGOL');

insert into BOOK (id, TITLE, AUTHOR_ID, GENRE_ID) values (1, 'THE CASTLE', 1, 1);
insert into BOOK (id, TITLE, AUTHOR_ID, GENRE_ID) values (2, 'THE GOVERNMENT INSPECTOR', 2, 2);
insert into BOOK (id, TITLE, AUTHOR_ID, GENRE_ID) values (3, 'DEAD SOULS', 2, 1);
