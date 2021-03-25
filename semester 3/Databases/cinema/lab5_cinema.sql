USE [Cinema]
GO

--USE ACTOR AS Ta
--USE Movie AS Tb
--USE MoviesAndActors AS Tc

--A: CLUSTERED INDEX SCAN
SELECT * FROM Actor;

--CLUSTERED INDEX SEEK
SELECT * FROM Actor WHERE id=100;

--NONCLUSTERED INDEX SCAN
SELECT CNP FROM Actor; 

--NONCLUSTERED INDEX SEEK
SELECT CNP FROM Actor WHERE CNP='6000123456789';

--KEY LOOKUP
SELECT * FROM Actor WHERE CNP='6000123456789';

--B
SELECT Rating FROM Movie WHERE Movie.Rating=5; --CLUSTERED INDEX SCAN

IF EXISTS(SELECT NAME FROM sys.indexes WHERE NAME='MADindex')
	DROP INDEX RatingIndex ON Movie;

CREATE INDEX RatingIndex
	ON Movie(Rating);

SELECT Rating FROM Movie WHERE Movie.Rating=5; --NONCLUSTERED INDEX SEEK

--C
CREATE OR ALTER VIEW ViewC AS
	SELECT Movie.id AS MovieID, Movie.Name AS MovieName, Actor.id AS ActorID, Actor.Name AS ActorName  
	FROM Movie 
		INNER JOIN MoviesAndActors ON Movie.id=MoviesAndActors.idMovie AND Movie.Name='LALALALA'
			INNER JOIN Actor ON Actor.id=MoviesAndActors.idActor;
GO

SELECT * FROM ViewC; --NONCLUSTERED INDEX SCAN ON MoviesAndActor
                     --CLUSTERED INDEX SEEK ON Movie
					 --CLUSTERED INDEX SEEK ON Actor

IF EXISTS(SELECT NAME FROM sys.indexes WHERE NAME='MADindex')
	DROP INDEX NameIndex ON Movie;

CREATE INDEX NameIndex
	ON Movie(Name);

SELECT * FROM ViewC; --NONCLUSTERED INDEX SCAN ON MoviesAndActor
                     --NONCLUSTERED INDEX SEEK ON Movie
					 --CLUSTERED INDEX SEEK ON Actor