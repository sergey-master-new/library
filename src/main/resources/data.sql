INSERT INTO THEMES (THEME_NAME)
VALUES ('Novel'),
       ('Fantastic'),
       ('Economic'),
       ('Science'),
       ('Other');

INSERT INTO PUBLISHERS (PUBLISHER_NAME)
VALUES ('Eksmo'),
       ('Rosmen'),
       ('Ernst & Young'),
       ('RIPOL classic'),
       ('Publishing house "Lev"');

INSERT INTO AUTHORS (LAST_NAME, FIRST_NAME, PATRONOMIC)
VALUES ('Tolstoy', 'Lev', 'Nikolarvich'),
       ('Efremov', 'Ivan', 'Antonovich'),
       ('Romanov', 'S.', 'G.'),
       ('Popov', 'V.', 'L.'),
       ('Serov', 'M.', 'R.'),
       ('Ivanov', 'D.', 'D.'),
       ('Altshuller', 'Genrih', '');

INSERT INTO TYPES (TYPE_NAME)
VALUES ('Book'),
       ('Newspaper'),
       ('Magazine'),
       ('Other');

INSERT INTO PUBLICATIONS (DATE_OF_PUBLICATION, PUBLICATION_NAME, PAGE_COUNT)
VALUES (PARSEDATETIME('1976','yyyy'), 'War and peace', '400'),
       (PARSEDATETIME('1968','yyyy'), 'Bull time', '200'),
       ('2021-07-01', 'Businessman №46', '18'),
       ('2021-01-01', 'Businessman №47', '18'),
       ('2021-01-01', 'World machinery', '18');

INSERT INTO PUBLICATION_AUTHOR (PUBLICATION_ID, AUTHOR_ID)
VALUES (1, 1),
       (2, 2),
       (3, 3),
       (3, 4),
       (4, 4),
       (4, 5),
       (5, 6),
       (5, 7);

INSERT INTO PUBLICATION_PUBLISHER (PUBLICATION_ID, PUBLISHER_ID)
VALUES (1, 1),
       (2, 2),
       (3, 3),
       (4, 3),
       (5, 5);

INSERT INTO PUBLICATION_THEME (PUBLICATION_ID, THEME_ID)
VALUES (1, 1),
       (2, 2),
       (3, 3),
       (4, 3),
       (5, 4);

INSERT INTO PUBLICATION_TYPE (PUBLICATION_ID, TYPE_ID)
VALUES (1, 1),
       (2, 1),
       (3, 2),
       (4, 2),
       (5, 3);

