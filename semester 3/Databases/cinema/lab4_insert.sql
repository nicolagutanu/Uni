USE [Cinema]
GO

CREATE OR ALTER PROCEDURE insertRows @noRows INT, @table VARCHAR(100)
AS
	IF @noRows<1
		RAISERROR('INVALID NUMBER OF ROWS', 10,1);
		
		DECLARE @c INT;
		DECLARE @idMovie INT;
		DECLARE @nameM CHAR(200);
		DECLARE @noSeats INT;
		DECLARE @idDirector INT;
		DECLARE @rating REAL;
		DECLARE @idStudio INT;
		DECLARE @idCountry INT;
		DECLARE @nameD CHAR(200);
		DECLARE @noMovies INT;
		DECLARE @nameS CHAR(200);
		DECLARE @nameC CHAR(200);
		DECLARE @continent CHAR(200);
		
		--select * from MoviesAndDirector;
		--select * from Director;
		--SELECT * FROM Studios;
		--SELECT * FROM CountryOfOrigin;
		--select * from Movie;

		DBCC CHECKIDENT('Studios', RESEED, 0);
		DBCC CHECKIDENT('Movie', RESEED, 0);
		DBCC CHECKIDENT('MoviesAndDirector', RESEED, 0);

		SET @c=0;
		IF @table='Studios'
		BEGIN
			WHILE @c<@noRows
			BEGIN
				SET @nameS='STUDIO '+CONVERT(VARCHAR(10),@c);
				SET @noMovies=(SELECT FLOOR(RAND()*(100-10+1)+10));
				INSERT INTO Studios VALUES (@nameS, @noMovies);

				SET @c=@c+1;
			END
		END

		IF @table='Movie'
		BEGIN
			WHILE @c<@noRows
			BEGIN
				SET @idStudio=(SELECT FLOOR(RAND()*(@noRows-1+1)+1));

				SET @nameC='COUNTRY '+CONVERT(VARCHAR(10),@c);
				SET @continent='CONTINENT '+CONVERT(VARCHAR(10),@c);
				INSERT INTO CountryOfOrigin VALUES (@nameC, @continent);
				SELECT @idCountry=id FROM CountryOfOrigin WHERE @nameC=Name;

				SET @nameM='MOVIE '+CONVERT(VARCHAR(10),@c);
				SET @rating=(SELECT ROUND(RAND()*(10-0+1)+0, 1));
				INSERT INTO Movie VALUES (@nameM, @rating, @idStudio, @idCountry);

				SET @c=@c+1;
			END
		END

		IF @table='MoviesAndDirector'
		BEGIN
			WHILE @c<@noRows
			BEGIN
				SET @nameD='DIRECTOR '+CONVERT(VARCHAR(10),@c);
				SET @noMovies=(SELECT FLOOR(RAND()*(500-10+1)+10));
				INSERT INTO Director VALUES (@nameD, @noMovies);
				SELECT @idDirector=id FROM Director WHERE @nameD=Name;

				SET @idMovie=(SELECT FLOOR(RAND()*(@noRows-1+1)+1));

				INSERT INTO MoviesAndDirector VALUES (@idMovie, @idDirector);

				SET @c=@c+1;
			END
		END
GO