
--1
CREATE TABLE TrainTypes(
	TrainTypesId INT PRIMARY KEY,
	TName VARCHAR(50),
	TDescription VARCHAR(30)
);

CREATE TABLE Trains(
	TrainID INT PRIMARY KEY,
	TName VARCHAR(30),
	TrainTypeID INT REFERENCES TrainTypes(TrainTypesID)
);

CREATE TABLE Routes(
	RouteID INT PRIMARY KEY,
	RName VARCHAR(50) UNIQUE,
	TrainID INT REFERENCES Trains(TrainID)
);

CREATE TABLE Stations(
	StationID INT PRIMARY KEY,
	SName VARCHAR(5) UNIQUE
);

CREATE TABLE StationsRoutes(
	StationID INT REFERENCES Stations(StationID),
	RouteID INT REFERENCES Routes(RouteID),
	Arrival TIME,
	Departure TIME,
	PRIMARY KEY(StationID,RouteID)
);
GO

--2
CREATE OR ALTER PROC uspUpdateRoute(@Rname VARCHAR(50), @StationName VARCHAR(50), @Arrival TIME, @Departure Time)
AS
	DECLARE @SID INT, @RID INT
	IF NOT EXISTS(SELECT * FROM Stations WHERE SName = @StationName)
	BEGIN
		RAISERROR('Invalid station name', 16,1)
		RETURN
	END

	IF NOT EXISTS(SELECT * FROM Routes WHERE RName = @Rname)
	BEGIN
		RAISERROR('Invalid route name', 16,1)
		RETURN
	END

	SELECT @SID = (SELECT StationID FROM Stations WHERE SName = @StationName),
		@RID = (SELECT RouteID FROM Routes WHERE RName = @Rname)

	IF EXISTS(SELECT*
				FROM StationsRoutes
				WHERE StationID = @SID and RouteID = @RID)
			UPDATE StationsRoutes
			set Arrival = @Arrival, Departure = @Departure
			WHERE StationID = @SID AND RouteID = @RID
	ELSE
		INSERT StationsRoutes(StationID,RouteID,Arrival,Departure)
		VALUES(@SID,@RID,@Arrival,@Departure)
GO

INSERT TrainTypes VALUES(1,'type1','descr'),(2, 'type2','descr')
INSERT Trains VALUES(1,'t1',1),(2,'t2',1),(3,'t3',1)
INSERT Routes VALUES(1,'r1',1),(2,'r2',2),(3,'r3',3)
INSERT Stations VALUES(1,'s1'),(2,'s2'),(3,'s3')

SELECT * FROM TrainTypes
SELECT * FROM Trains
SELECT * FROM Routes
SELECT * FROM Stations
SELECT * FROM StationsRoutes
ORDER BY RouteID

EXEC uspUpdateRoute @RName = 'r1', @StationName = 's1', @Arrival = '5:50', @Departure = '6:10'
EXEC uspUpdateRoute @RName = 'r1', @StationName = 's2', @Arrival = '6:50', @Departure = '7:10'
EXEC uspUpdateRoute @RName = 'r1', @StationName = 's3', @Arrival = '7:50', @Departure = '8:10'

EXEC uspUpdateRoute @RName = 'r2', @StationName = 's1', @Arrival = '5:50', @Departure = '6:10'
EXEC uspUpdateRoute @RName = 'r2', @StationName = 's2', @Arrival = '6:50', @Departure = '7:10'
EXEC uspUpdateRoute @RName = 'r2', @StationName = 's3', @Arrival = '7:50', @Departure = '8:10'

EXEC uspUpdateRoute @RName = 'r3', @StationName = 's1', @Arrival = '5:50', @Departure = '6:10'

--3
--SELECT StationId 
--FROM Stations
--EXCEPT
--SELECT StationId
--FROM StationsRoutes
--WHERE RouteID=1

--SELECT StationId 
--FROM Stations
--EXCEPT
--SELECT StationId
--FROM StationsRoutes
--WHERE RouteID=3


CREATE OR ALTER VIEW vRoutesWithAllStations
AS
SELECT R.RName
FROM Routes R
WHERE NOT EXISTS
	(SELECT StationId 
	FROM Stations
	EXCEPT
	SELECT StationId
	FROM StationsRoutes
	WHERE RouteID=R.RouteID)
GO

SELECT *
FROM vRoutesWithAllStations

SELECT * FROM Stations
SELECT * FROM StationsRoutes
ORDER BY RouteID

--4
SELECT S.SName
FROM Stations S
WHERE S.StationID IN
	(SELECT SR.StationID
	FROM StationsRoutes SR
	GROUP BY SR.StationID
	HAVING COUNT(*)>2)

CREATE OR ALTER FUNCTION ufFilterStationsByNumberOfRoutes(@R INT)
RETURNS TABLE
RETURN SELECT S.SName
	FROM Stations S
	WHERE S.StationID IN
		(SELECT SR.StationID
		FROM StationsRoutes SR
		GROUP BY SR.StationID
		HAVING COUNT(*)>@R)
GO

SELECT *
FROM ufFilterStationsByNumberOfRoutes(3)


