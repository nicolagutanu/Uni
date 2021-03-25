USE [Cinema]

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

ALTER TABLE Actor
ADD CNP char(13) UNIQUE;

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
	id int IDENTITY,
	idMovie int,
	idDirector int,
	FOREIGN KEY (idMovie) REFERENCES Movie(id) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (idDirector) REFERENCES Director(id) ON DELETE CASCADE ON UPDATE CASCADE,
	PRIMARY KEY(idMovie, idDirector)
)