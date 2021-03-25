USE [Cinema]
GO

--INSERT VALUES

INSERT INTO CountryOfOrigin VALUES ('USA', 'NORTH AMERICA'),
('ITALY', 'EUROPE'),
('UK', 'EUROPE'),
('SINGAPORE', 'ASIA'),
('JAPAN', 'ASIA');

INSERT INTO Studios VALUES ('WARNER BROS', 20),
('TOHO', 6),
('WALT DISNEY', 10),
('UNIVESAL', 17),
('SONY PICTURES', 4);

SELECT * FROM Studios; 

INSERT INTO  Movie VALUES ('CRAZY RICH ASIANS', 6.9, 4, 4),
('SPIRITED AWAY', 8.6, 2, 5),
('THE HUNGER GAMES', 7.2, 4, 1),
('CALL ME BY YOUR NAME', 7.9, 5, 2),
('FANTASTIC BEATS AND WHERE TO FIND THEM', 7.3, 1, 3),
('FAST & FURIOUS 6', 7.1, 4, 3),
('THE GODFATHER', 9.2, 5, 2),
('UNDER A PINK SKY', 5.2, 3, 4),
('PS. I STILL LOVE YOU', 7.1, 1, 1),
('MULAN', 7.6, 3, 5);

INSERT INTO  Movie VALUES ('NOW YOU SEE ME', 6.9, 4, 4);

select * from Movie;

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

--INSERT INTO MoviesAndDirector VALUES (13,7);

--UPDATE DATA

UPDATE Studios SET nrOfMoviesPlanned=nrOfMoviesPlanned-2 WHERE Name='TOHO' OR Name='UNIVESAL';
SELECT * FROM Studios;
UPDATE Venue SET nrOfSeats=nrOfSeats-20 WHERE nrOfSeats>=250;
SELECT * FROM Venue;
UPDATE Director SET nrOfMovies+=1 WHERE nrOfMovies IS NULL;
SELECT * FROM Director;
UPDATE Movie SET Rating+=0.5 WHERE Name LIKE 'T%';
SELECT * FROM Movie;

--DELETE DATA

DELETE FROM Actor WHERE Name='ALEXANDER ROBERTSON';
SELECT * FROM Actor;
DELETE FROM Venue WHERE nrOfSeats BETWEEN 270 AND 300;
SELECT * FROM Venue;
INSERT INTO Actor VALUES ('LAURENT VERRE', 27);
SELECT * FROM Actor;
DELETE FROM Actor WHERE nrOfMovies IN (3, 27);
SELECT * FROM Actor;
DELETE FROM Genres WHERE mostSearchedFor=0.47 OR mostSearchedFor<=0.40;
SELECT * FROM Genres;

--A:

--SELECT ALL ACTORS AND DIRECTORS THAT HAVE WORKED ON MOVIE NR 10 ORDERED BY NAME
SELECT A.Name FROM Actor A, MoviesAndActors MA WHERE MA.idMovie=10 AND A.idActor=MA.idActor UNION 
SELECT D.Name FROM Director D, MoviesAndDirector MD WHERE MD.idMovie=10 AND D.idDirector=MD.idDirector ORDER BY Name;
--SELECT ACTORS THAT HAVE BEEN IN MORE THAN 20 MOVIES AND DIRECTORS THAT HAVE DIRECTED MORE THAN 10 MOVIES AND WHOSE NAME STARTS WITH A
SELECT Name FROM Actor WHERE nrOfMovies>=20 UNION 
SELECT Name FROM Director WHERE nrOfMovies>=10 or Name LIKE 'A%';

--B: 

--SELECT VENUES THAT ARE IN CLUJ-NAPOCA OR MILAN
SELECT Name FROM Venue WHERE City IN ('CLUJ-NAPOCA', 'MILAN');
INSERT INTO CountryOfOrigin VALUES ('BRAZIL', 'SOUTH AMERICA');
SELECT * FROM CountryOfOrigin;
--SELECT ALL COUNTRIES THAT ARE SHOWING A MOVIE
SELECT Movie.idCountryOfOrigin, CountryOfOrigin.Name FROM Movie, CountryOfOrigin INTERSECT 
SELECT CountryOfOrigin.idCountry, Name FROM CountryOfOrigin;


--C: 

--SHOW ALL MOVIES EXCEPT THE ONES STARTING WITH A T
SELECT DISTINCT Name FROM Movie EXCEPT
SELECT Name FROM Movie WHERE Name LIKE 'T%';
SELECT * FROM Actor;
--SELECT ALL ACTORS THAT ARE NOT VIN DIESEL, JOSH HUCHERSON OR KOO MOON YONG
SELECT TOP 10 Name FROM Actor WHERE Name NOT IN ('VIN DIESEL', 'JOSH HUCHERSON', 'KOO MOON YONG');

--D: 

--SELECT ALL MOVIES WITH THEIR RESPECTIVE COUNTRIES OF ORIGIN AND THE STUDIOS THAT FINANCED THEM
SELECT Movie.Name, CountryOfOrigin.Name, Studios.Name FROM ((Movie 
INNER JOIN CountryOfOrigin ON Movie.idCountryOfOrigin=CountryOfOrigin.idCountry)
INNER JOIN Studios ON Movie.idStudio=Studios.idStudio);

--SELECT ALL MOVIES AND THEIR RESPECTIVE DIRECTORS
INSERT INTO  Movie VALUES ('NOW YOU SEE ME', 6.9, 4, 4);
SELECT Movie.Name, MoviesAndDirector.idDirector FROM Movie 
LEFT JOIN MoviesAndDirector ON Movie.idMovie=MoviesAndDirector.idMovie ;

--SELECT ALL ACTORS AND DIRECTORS WITH WHICH THEY HAVE WORKED WITH
SELECT DISTINCT MoviesAndActors.idActor, MoviesAndDirector.idDirector FROM MoviesAndActors
RIGHT JOIN MoviesAndDirector ON MoviesAndActors.idMovie=MoviesAndDirector.idMovie;

INSERT INTO Venue VALUES ('LONDON', 'VIEW PLAZA', 400);
--SELECT ALL MOVIES AND SHOW THEIR VENUE IDS
SELECT Movie.Name, MoviesAndVenues.idVenue FROM Movie
FULL OUTER JOIN MoviesAndVenues ON Movie.idMovie=MoviesAndVenues.idMovie;

DELETE FROM Venue WHERE idVenue=8;
SELECT * FROM Venue;

--E

SELECT * FROM CountryOfOrigin;
DELETE FROM CountryOfOrigin WHERE idCountry=7;

--SELECT ALL MOVIES FILMED IN ASIA
SELECT Movie.Name FROM Movie WHERE Movie.idCountryOfOrigin IN 
(SELECT CountryOfOrigin.idCountry FROM CountryOfOrigin WHERE Continent='ASIA') ORDER BY Name;

--SELECT ALL MOVIES IN WHICH ACTORS WITH MORE THAN 10 MOVIES HAVE PLAYED
SELECT TOP 5 Movie.Name FROM Movie WHERE Movie.idMovie IN
	(SELECT MoviesAndActors.idActor FROM MoviesAndActors WHERE MoviesAndActors.idActor IN
		(SELECT Actor.idActor FROM Actor WHERE Actor.nrOfMovies>=10)
	);

--F

--SELECT ALL MOVIES THAT HAVE RATINGS BIGGER THAN 7 ORDERED BY RATING
SELECT * FROM Studios;
SELECT Movie.Name FROM Movie WHERE EXISTS(
SELECT * FROM Studios WHERE Studios.idStudio=Movie.idStudio AND Movie.Rating>7) ORDER BY Movie.Rating DESC;

--SELECT ALL VENUES THAT SHOW TH HUNGER GAMES
SELECT DISTINCT Venue.Name FROM Venue WHERE EXISTS(
SELECT * FROM MoviesAndVenues, Movie WHERE MoviesAndVenues.idVenue=Venue.idVenue AND MoviesAndVenues.idMovie=Movie.idMovie AND Movie.Name='THE HUNGER GAMES'); 

--G

--SELECT ALL VENUES THAT HAVE MORE SEATS THAN THE AVERAGE NUMBER IN ALL THE DATABASE
SELECT Venue.Name FROM (SELECT AVG(nrOfSeats) AS AVGSEATS FROM Venue) AS V, Venue
WHERE Venue.nrOfSeats>V.AVGSEATS;
--SELECT ALL GENRES THAT HAVE RATING BIGGER THAN AVERAGE RATING
SELECT Genres.Name FROM (SELECT AVG(mostSearchedFor) AS MSF FROM Genres) AS G, Genres
WHERE Genres.mostSearchedFor>G.MSF;

--H

--SHOW NUMBER OF MOVIES FILMED IN EVERY COUNTRY
SELECT COUNT(Movie.idMovie), Movie.idCountryOfOrigin FROM Movie
GROUP BY Movie.idCountryOfOrigin;

--SHOW ALL MOVIES HAVING THE AVERAGE RATING BIGGER THAN 8
SELECT Movie.Name, MAX(Movie.Rating) AS MaxRaT FROM Movie
GROUP BY Movie.Name
HAVING AVG(Movie.Rating)>8;

--SELECT ALL VENUES THAT HAVE THE MINIMUM OF SEATS BIGGER THAN THE AVERAGE
SELECT Venue.Name FROM Venue
GROUP BY Venue.Name
HAVING MIN(Venue.nrOfSeats)>(SELECT AVG(Venue.nrOfSeats) FROM Venue);

--SELECT ALL ACTORS WITH MAXIMUM NR OF MOVIES BIGGER THAN AVERAGE NUMBER OF MOVIES
SELECT Actor.Name FROM Actor 
GROUP BY Actor.Name
HAVING MAX(Actor.nrOfMovies)>(SELECT COUNT(Movie.idMovie) FROM Movie);


--I

--SELECT ALL MOVIES HAVING AS COUNTRY OF ORIGIN SOUTH AMERICA, NORTH AMERICA
SELECT Movie.Name FROM Movie WHERE Movie.idCountryOfOrigin=ANY(
SELECT CountryOfOrigin.idCountry FROM CountryOfOrigin WHERE CountryOfOrigin.Continent IN ('SOUNTH AMERICA', 'NORTH AMERICA'));

--
SELECT Movie.Name FROM Movie WHERE Movie.idStudio=ALL(
SELECT Studios.idStudio FROM Studios WHERE Studios.Name IN ('UNIVE', 'TO'));

--SELECT ALL MOVIES HAVING MIN RATING BIGGER THAN THE AVERAGE
SELECT DISTINCT Movie.Name FROM Movie 
GROUP BY Movie.Name
HAVING MIN(Movie.Rating)>ANY(SELECT AVG(Movie.Rating) FROM Movie);

--SELECT ALL ACTORS HAVING MIN NR OF MOVIES BIGGER THAN THE AVERAGE
SELECT Actor.Name FROM Actor
GROUP BY Name
HAVING MIN(Actor.nrOfMovies)>ALL(SELECT AVG(Actor.nrOfMovies) FROM Actor);