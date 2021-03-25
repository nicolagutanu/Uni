USE [Cinema]
GO

CREATE OR ALTER VIEW ViewStudios AS
SELECT * FROM Studios
GO

CREATE OR ALTER VIEW ViewMoviesWithRespectivStudios AS
SELECT Movie.Name AS MovieName, Studios.Name AS StudiosName FROM Movie 
INNER JOIN Studios ON Movie.idStudio=Studios.id
GO

CREATE OR ALTER VIEW ViewMovieWithRespectivDirectors AS
SELECT M.Name AS MovieName, D.Name AS DirectorName FROM Movie AS M 
LEFT JOIN MoviesAndDirector AS MAD ON M.id=MAD.idMovie
INNER JOIN Director AS D ON D.id=MAD.idDirector
GROUP BY M.Name, D.Name
GO