USE Cinema;
GO

--A: MODIFY THE TYPE OF A COLUMN
CREATE PROCEDURE modT
AS 
	ALTER TABLE Genres
	DROP CONSTRAINT CK__Genres__mostSear__6D0D32F4;
	ALTER TABLE Genres
	ALTER COLUMN [mostSearchedFor] real;
	PRINT N'modify the type of mostSearchedFor to real';
GO

CREATE PROCEDURE modTUndo
AS
	ALTER TABLE Genres
	ALTER COLUMN [mostSearchedFor] decimal(3,2);
	ALTER TABLE Genres
	ADD CONSTRAINT CK__Genres__mostSear__6D0D32F4 CHECK (mostSearchedFor BETWEEN 0 AND 1);
	PRINT N'modify the type of of mostSearchedFor back to decimal';
GO


--B: ADD/REMOVE A COLUMN
CREATE PROCEDURE addColumn
AS
	ALTER TABLE Studios
	ADD Buget int;
	PRINT N'add column Buget into Studios table';
GO

CREATE PROCEDURE removeColumn
AS
	ALTER TABLE Studios
	DROP COLUMN Buget;
	PRINT N'drop Buget column from Studios table';
GO


--C: ADD/REMOVE A DEFAULT CONSTRAINT
CREATE PROCEDURE addDefault
AS
	ALTER TABLE Movie
	ADD CONSTRAINT df_Name
	DEFAULT 'to be decided' FOR Name;
	PRINT N'make 0 the default value for nrOfMovies column of Diretor table'; 
GO

CREATE PROCEDURE removeDefault
AS
	ALTER TABLE Movie
	DROP CONSTRAINT df_Name;
	PRINT N'drop the default constraint on nrOfMovies column from Director table';
GO


--D: ADD/REMOVE A PRIMARY KEY
CREATE PROCEDURE removePrimaryKey
AS
	ALTER TABLE MoviesAndDirector
	DROP CONSTRAINT PK__MoviesAn__9DB8412F219EFA43;
	PRINT N'remove primary key idMA from MoviesAndActors table';
GO

CREATE PROCEDURE addPrimaryKey
AS
	ALTER TABLE MoviesAndDirector
	ADD CONSTRAINT PK__MoviesAn__9DB8412F219EFA43 PRIMARY KEY (idMD);
	PRINT N'add back primary key idMA to MoviesAndActors table';
GO


--E: ADD/REMOVE A CANDIDATE KEY
CREATE PROCEDURE addCandidateKey
AS
	ALTER TABLE Actor
	ADD CNP char(13) UNIQUE;
	PRINT N'add candidate key CNP to Actor table';
GO

CREATE PROCEDURE removeCandidateKey
AS
	ALTER TABLE Actor
	DROP COLUMN CNP;
	PRINT N'drop candidate key CNP from Actor table';
GO


--F: ADD/REMOVE A FOREIGN KEY
CREATE PROCEDURE removeForeignKey
AS
	ALTER TABLE Movie
	DROP CONSTRAINT FK__Movie__idStudio__2B3F6F97;
	PRINT N'drop foreign key idStudio from Movie table';
GO

CREATE PROCEDURE addForeignKey
AS
	ALTER TABLE Movie
	ADD CONSTRAINT FK__Movie__idStudio__2B3F6F97
	FOREIGN KEY (idStudio) REFERENCES Studios(idStudio) --ON DELETE CASCADE ON UPDATE CASCADE;
	PRINT N'add foreign key idStudio to Movie table';
GO


--G: CREATE/DROP A TABLE
CREATE PROCEDURE createTable
AS
	CREATE TABLE Profit(
	idProfit int IDENTITY(1,1) NOT NULL,
	movieName varchar(400),
	moneyMade int, CHECK (moneyMade>=0),
	inCinema int);
	PRINT N'create table Profit';
GO

CREATE PROCEDURE dropTable
AS
	DROP TABLE Profit;
	PRINT N'drop Profit table';
GO

CREATE TABLE Versions(
	version int);

INSERT INTO Versions VALUES (0);

CREATE PROCEDURE nextVersion @ver int
AS
	IF @ver=1
		EXEC modT;
	ELSE IF @ver=2
		EXEC addColumn;
	ELSE IF @ver=3
		EXEC addDefault;
	ELSE IF @ver=4
		EXEC removePrimaryKey;
	ELSE IF @ver=5
		EXEC addCandidateKey;
	ELSE IF @ver=6
		EXEC removeForeignKey;
	ELSE IF @ver=7
		EXEC createTable;
GO

CREATE PROCEDURE previousVersion @ver int
AS
	IF @ver=1
		EXEC modTUndo;
	ELSE IF @ver=2
		EXEC removeColumn;
	ELSE IF @ver=3
		EXEC removeDefault;
	ELSE IF @ver=4
		EXEC addPrimaryKey;
	ELSE IF @ver=5
		EXEC removeCandidateKey;
	ELSE IF @ver=6
		EXEC addForeignKey;
	ELSE IF @ver=7
		EXEC dropTable;
GO

CREATE PROCEDURE getCurrentVersion @ver int OUTPUT
AS
	SELECT @ver=Versions.version FROM Versions;
GO

CREATE PROCEDURE updateCurrentVersion @ver int
AS
	UPDATE Versions SET version=@ver;
GO

DROP PROCEDURE changeVersion
GO
CREATE PROCEDURE changeVersion @nextVer int
AS
	IF @nextVer<0 OR @nextVer>7
		RAISERROR('INEXISTENT VERSION', 10, 1);
	DECLARE @actualVer INT=0;
	EXEC getCurrentVersion @ver=@actualVer OUTPUT
	IF @nextVer=@actualVer
		RAISERROR('WE ARE ALREADY IN THE NEXT VERSION', 10, 1);
	DECLARE @counter INT=@actualVer;
	IF @counter<@nextVer
		WHILE @counter<@nextVer
		BEGIN
			SET @counter=@counter+1;
			EXEC nextVersion @ver=@counter
		END
	ELSE IF @counter>@nextVer
		WHILE @counter>=@nextVer
		BEGIN
			EXEC previousVersion @ver=@counter
			SET @counter=@counter-1;
		END
	EXEC updateCurrentVersion @ver=@nextVer;
GO