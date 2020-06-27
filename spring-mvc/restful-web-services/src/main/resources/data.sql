/*If the file name is not data.sql, it doesn't get automatically executed*/
INSERT INTO USER VALUES (1000, sysdate(), 'Custódia');
INSERT INTO USER VALUES (1001, sysdate(), 'Clemente');
INSERT INTO USER VALUES (1002, sysdate(), 'Peperutcha');

INSERT INTO POST VALUES (11000, 'My first post', 1000);
INSERT INTO POST VALUES (11001, 'My second post', 1000);
INSERT INTO POST VALUES (11002, 'Cool post', 1001);