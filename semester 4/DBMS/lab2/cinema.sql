USE [Cinema]

select @@SERVERNAME

CREATE TABLE CountryOfOrigin
(
	id int IDENTITY NOT NULL,
	Name char(200),
	Continent char(200),
	PRIMARY KEY (id)
)

CREATE TABLE Studios
(
	id int IDENTITY NOT NULL,
	Name char(200),
	nrOfMoviesPlanned int DEFAULT 0,
	PRIMARY KEY (id)
)

CREATE TABLE  Movie
(
	id int IDENTITY NOT NULL,
	Name varchar(400),
	Rating real CHECK (Rating>=0),
	idStudio int NOT NULL,
	idCountryOfOrigin int NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (idStudio) REFERENCES Studios(id) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (idCountryOfOrigin) REFERENCES CountryOfOrigin(id) ON DELETE CASCADE ON UPDATE CASCADE
)

CREATE TABLE Director
(
	id int IDENTITY NOT NULL,
	Name char(200),
	nrOfMovies int DEFAULT 0,
	PRIMARY KEY (id)
)

CREATE TABLE Venue
(
	id int IDENTITY NOT NULL,
	City char(200),
	Name char(200),
	nrOfSeats int CHECK(nrOfSeats>50),
	PRIMARY KEY (id)
)

CREATE TABLE Genres
(
	id int IDENTITY NOT NULL,
	Name char(100),
	mostSearchedFor decimal(3,2), CHECK (mostSearchedFor BETWEEN 0 AND 1),
	PRIMARY KEY (id)
)

CREATE TABLE Actor
(
	id int IDENTITY NOT NULL,
	Name char(200),
	nrOfMovies int DEFAULT 0,
	PRIMARY KEY (id)
)

CREATE TABLE MoviesAndActors
(
	id int IDENTITY NOT NULL PRIMARY KEY,
	idMovie int,
	idActor int,
	FOREIGN KEY (idMovie) REFERENCES Movie(id) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (idActor) REFERENCES Actor(id) ON DELETE CASCADE ON UPDATE CASCADE,
	UNIQUE(idMovie, idActor)
)

CREATE TABLE MoviesAndVenues
(
	id int IDENTITY NOT NULL PRIMARY KEY,
	idMovie int,
	idVenue int,
	FOREIGN KEY (idMovie) REFERENCES Movie(id) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (idVenue) REFERENCES Venue(id) ON DELETE CASCADE ON UPDATE CASCADE,
	UNIQUE(idMovie, idVenue)
)

CREATE TABLE MovieAndGenres
(
	id int IDENTITY NOT NULL PRIMARY KEY,
	idMovie int,
	idGenre int,
	FOREIGN KEY (idMovie) REFERENCES Movie(id) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (idGenre) REFERENCES Genres(id) ON DELETE CASCADE ON UPDATE CASCADE,
	UNIQUE(idMovie, idGenre)
)

CREATE TABLE MoviesAndDirector
(
	id int IDENTITY NOT NULL PRIMARY KEY,
	idMovie int,
	idDirector int,
	FOREIGN KEY (idMovie) REFERENCES Movie(id) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (idDirector) REFERENCES Director(id) ON DELETE CASCADE ON UPDATE CASCADE,
	UNIQUE(idMovie, idDirector)
)

select * from Movie
select * from CountryOfOrigin
select * from Actor
select * from Director
select * from Studios
select * from Venue
select * from Genres
select * from MovieAndGenres
select * from MoviesAndActors
select * from MoviesAndDirector
select * from MoviesAndVenues

INSERT INTO CountryOfOrigin VALUES ('USA', 'NORTH AMERICA'),
('ITALY', 'EUROPE'),
('UK', 'EUROPE'),
('SINGAPORE', 'ASIA'),
('JAPAN', 'ASIA');

INSERT INTO  Movie VALUES ('CRAZY RICH ASIANS', 6.9, 4, 4),
('SPIRITED AWAY', 8.6, 2, 5),
('THE HUNGER GAMES', 7.2, 4, 1),
('CALL ME BY YOUR NAME', 7.9, 5, 2),
('FANTASTIC BEATS AND WHERE TO FIND THEM', 7.3, 1, 3),
('FAST & FURIOUS 6', 7.1, 4, 3),
('THE GODFATHER', 9.2, 5, 2),
('UNDER A PINK SKY', 5.2, 3, 4),
('PS. I STILL LOVE YOU', 7.1, 1, 1),
('MULAN', 7.6, 3, 5),
('NOW YOU SEE ME', 6.9, 4, 4);;

INSERT INTO Director VALUES ('DAVID MIKISON', 4),
('RENEE ROBINSON', 8),
('ANDREW MINYARD', 7),
('KEVIN DAY', 3),
('NICKY HEMMIK', 1),
('NEIL JOSTEN', 4),
('DAVIC WHYMACK', 18),
('ALLYSON REYNOLDS', 12);

INSERT INTO Venue VALUES ('CLUJ-NAPOCA', 'CINEMA CITY', 200),
('MILAN', 'ODEON', 100),
('PARIS', 'LA CHIC', 73),
('CHICAGO', 'SHOWPLACE ICON', 300),
('FUKUOKA', 'AMC', 276),
('AUCKLAND', 'OPERA CITY', 190);

INSERT INTO Genres VALUES ('COMEDY', 0.73),
('HORROR', 0.47),
('THRILLER', 0.59),
('ROMANCE', 0.61),
('ANIMATED', 0.63),
('FANTASY', 0.70),
('ACTION', 0.75);

INSERT INTO Actor VALUES ('VIN DIESEL', 30),
('TIMOTHEE CHALAMET', 6),
('LANA CONDOR', 5),
('KRISTEN BELL', 35),
('RACHEL CHO', 3),
('KOO MOON YONG', 23),
('ALEXANDER ROBERTSON', 17),
('JENNIFER LAWRANCE', 26),
('JOSH HUCHERSON', 15),
('LIAM HEMSWORTH', 22),
('MOON GANG TAE', 10),
('PAUL WALKER', 19),
('NOAH CENTINEO', 4),
('MING-NA-WEN', 23),
('EDDIE MURPHY', 43),
('EDDIE REDMAYNE', 16);

INSERT INTO MoviesAndActors VALUES (1, 5),
(2, 6),
(2, 11),
(3, 8),
(3, 9),
(3, 10),
(4, 2),
(5, 7),
(5, 16),
(6, 1),
(6, 12),
(7, 7),
(7, 1),
(8, 6),
(8, 15),
(9, 3),
(9, 13),
(9, 4),
(10, 4),
(10, 14),
(10, 15);

INSERT INTO MoviesAndVenues VALUES (1, 3),
(1, 6),
(1, 2),
(2, 3),
(3, 5),
(3, 6),
(4, 1),
(4, 2),
(4, 3),
(5, 5),
(6, 6),
(6, 1),
(7, 4),
(7, 2),
(8, 1),
(9, 6),
(9, 2),
(10, 1),
(10,3);

INSERT INTO MovieAndGenres VALUES (1, 1),
(1, 4),
(2, 5),
(3, 6),
(3, 7),
(4, 4),
(5, 6),
(5, 7),
(6, 3),
(6, 7),
(7, 3),
(7, 7),
(8, 1),
(8, 4),
(8, 2),
(9, 4),
(10, 5),
(10,7);

INSERT INTO MoviesAndDirector VALUES (1, 1),
(2, 5),
(2, 4),
(3, 3),
(3, 6),
(4, 2),
(5, 2),
(5, 8),
(5, 7),
(6, 1),
(6, 7),
(7, 7),
(8, 3),
(9, 2),
(9, 6),
(10, 4),
(10, 6);
