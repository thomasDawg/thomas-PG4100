CREATE SCHEMA IF NOT EXISTS`pg4100innlevering2` ;

DROP TABLE bokliste;
CREATE TABLE bokliste
(
forfatter varchar(20) not null,
tittel varchar(30) not null,
ISBN varchar(20) not null,
sider smallint(4) not null,
utgitt smallint(4) not null
);

INSERT INTO `bokliste` (`forfatter`, `tittel`, `ISBN`, `sider`, `utgitt`) VALUES
('NYGÅRDSHAUG, GERT', 'MENGELE ZOO', '978-82-02-28849-5', 455, 2008),
('DIAMOND, JARED', 'GUNS, GERMS AND STEEL', '0-099-30278-0', 480, 2005),
('KEHLMANN, DANIEL', 'OPPMÅLINGEN AV VERDEN', '978-82-05-38839-0', 250, 2008),
('ESPEDAL, TOMAS', 'IMOT KUNSTEN', '978-82-05-39616-6', 164, 2009),
('TOLKIEN, J. R. R.', 'THE HOBBIT', '0048230707', 279, 1966),
('ECO, UMBERTO', 'ROSENS NAVN', '82-10-02718-2', 551, 1985),
('ATWOOD, MARGARET', 'THE YEAR OF THE FLOOD', '978-1-84408-564-4', 518, 2010),
('NESBØ, JO', 'SØNNEN', '978-8-20335-593-6', 422, 2014) ;
