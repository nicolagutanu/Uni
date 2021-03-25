USE [Cinema]
GO

CREATE OR ALTER PROCEDURE runTests
	@table1 VARCHAR(100),
	@table2 VARCHAR(100),
	@table3 VARCHAR(100),
	@view1 VARCHAR(100),
	@view2 VARCHAR(100),
	@view3 VARCHAR(100),
	@noRows INT,
	@delete VARCHAR(100),
	@insert VARCHAR(100)
AS
	DECLARE @testID INT;
	DECLARE @tableID INT;
	DECLARE @rows INT;
	DECLARE @pos INT;
	DECLARE @viewID INT;
	DECLARE @c INT;

	--select * from Tests;
	--select * from Tables;
	--select * from TestTables;
	--select * from Views;
	--select * from TestViews;
	--select * from TestRuns;
	--select * from TestRunTables;
	--select * from TestRunViews;

	--INSERT INTO TABLES
	INSERT INTO Tables VALUES (@table1),
	(@table2),
	(@table3);

	--INSERT INTO VIEWS
	INSERT INTO Views VALUES (@view1),
	(@view2),
	(@view3);

	--INSERT INTO TESTS
	INSERT INTO Tests VALUES (@delete),
	(@insert),
	(@view1),
	(@view2),
	(@view3);

	--INSERT INTO TestViews
	SELECT @testID=T.TestID FROM Tests T WHERE T.Name=@view1;
	SELECT @viewID=V.ViewID FROM Views V WHERE V.Name=@view1;
	INSERT INTO TestViews VALUES (@testID, @viewID);
	SELECT @testID=T.TestID FROM Tests T WHERE T.Name=@view2;
	SELECT @viewID=V.ViewID FROM Views V WHERE V.Name=@view2;
	INSERT INTO TestViews VALUES (@testID, @viewID);
	SELECT @testID=T.TestID FROM Tests T WHERE T.Name=@view3;
	SELECT @viewID=V.ViewID FROM Views V WHERE V.Name=@view3;
	INSERT INTO TestViews VALUES (@testID, @viewID);

	--INSERT INTO TestTables FOR DELETE
	SET @pos=1;
	SELECT @testID=T.TestID FROM Tests T WHERE T.Name=@delete;
	SELECT @tableID=T.TableID FROM Tables T WHERE T.Name=@table3;
	INSERT INTO TestTables VALUES (@testID, @tableID, @noRows, @pos);
	SET @pos=@pos+1;
	SELECT @tableID=T.TableID FROM Tables T WHERE T.Name=@table2;
	INSERT INTO TestTables VALUES (@testID, @tableID, @noRows, @pos);
	SET @pos=@pos+1;
	SELECT @tableID=T.TableID FROM Tables T WHERE T.Name=@table1;
	INSERT INTO TestTables VALUES (@testID, @tableID, @noRows, @pos);
	SET @pos=@pos+1;

	--INSERT INTO TestTables FOR INSERT
	SELECT @testID=T.TestID FROM Tests T WHERE T.Name=@insert;
	SELECT @tableID=T.TableID FROM Tables T WHERE T.Name=@table1;
	INSERT INTO TestTables VALUES (@testID, @tableID, @noRows, @pos);
	SET @pos=@pos+1;
	SELECT @tableID=T.TableID FROM Tables T WHERE T.Name=@table2;
	INSERT INTO TestTables VALUES (@testID, @tableID, @noRows, @pos);
	SET @pos=@pos+1;
	SELECT @tableID=T.TableID FROM Tables T WHERE T.Name=@table3;
	INSERT INTO TestTables VALUES (@testID, @tableID, @noRows, @pos);

	--MAKE CURSOR TO GO THROUGH TestTables ONE BY ON
	SET @c=0;
	DECLARE goThroughTestTables CURSOR
	FOR
		SELECT TT.TestID, TT.TableID, TT.NoOfRows
		FROM TestTables TT
		ORDER BY TT.Position;

	OPEN goThroughTestTables
	FETCH goThroughTestTables
	INTO @testID, @tableID, @rows
	EXEC runInsDel @testID, @tableID, @rows

	WHILE @c<5
	BEGIN
		FETCH goThroughTestTables
		INTO @testID, @tableID, @rows
		EXEC runInsDel @testID, @tableID, @rows
		SET @c=@c+1;
	END

	CLOSE goThroughTestTables
	DEALLOCATE goThroughTestTables

	----MAKE CURSOR TO GO THROUGH TestViews ONE BY ONE
	SET @c=0
	DECLARE goThroughTestViews CURSOR
	FOR
		SELECT TV.TestID, TV.ViewID
		FROM TestViews TV
		ORDER BY TV.TestID

	OPEN goThroughTestViews
	FETCH goThroughTestViews
	INTO @testID, @viewID
	EXEC runView @viewID, @testID

	WHILE @c<2
	BEGIN
		FETCH goThroughTestViews
		INTO @testID, @viewID
		EXEC runView @viewID, @testID
		SET @c=@c+1;
	END

	CLOSE goThroughTestViews
	DEALLOCATE goThroughTestViews

	SELECT * FROM TestRuns;
	SELECT * FROM TestRunTables;
	SELECT * FROM TestRunViews;

	EXEC deleteAllRows TestRuns;
	EXEC deleteAllRows TestRunTables;
	EXEC deleteAllRows TestRunViews;
	EXEC deleteAllRows TestTables;
	EXEC deleteAllRows TestViews;
	EXEC deleteAllRows Tables;
	EXEC deleteAllRows Views;
	EXEC deleteAllRows Tests;
	--EXEC deleteAllRows @table3;
	--EXEC deleteAllRows @table2;
	--EXEC deleteAllRows @table1;

	DBCC CHECKIDENT('TestRuns', RESEED, 0);
	DBCC CHECKIDENT('Tables', RESEED, 0);
	DBCC CHECKIDENT('Views', RESEED, 0);
	DBCC CHECKIDENT('Tests', RESEED, 0);
GO

CREATE OR ALTER PROCEDURE runView
	@viewID INT,
	@testID INT
AS
	DECLARE @startTime DATETIME;
	DECLARE @endTime DATETIME;
	DECLARE @viewName VARCHAR(100);
	DECLARE @desc VARCHAR(500);
	DECLARE @command NVARCHAR(100);

	SELECT @viewName=T.Name FROM Tests T WHERE T.TestID=@testID;
	SET @startTime=GETDATE();
	SET @command='SELECT * FROM '+@viewName;
	EXECUTE sp_executesql @command;
	SET @endTime=GETDATE();

	SET @desc='TEST RESULTS FOR '+@viewName;
	INSERT INTO TestRuns VALUES (@desc, @startTime, @endTime);
	SET @testID=(SELECT MAX(TestRunID) FROM TestRuns);
	INSERT INTO TestRunViews VALUES (@testID, @viewID, @startTime, @endTime);
GO

CREATE OR ALTER PROCEDURE runInsDel 
	@testID INT,
	@tableID INT,
	@noRows INT
AS
	DECLARE @startTime DATETIME;
	DECLARE @endTime DATETIME;
	DECLARE @testName VARCHAR(100);
	DECLARE @tableName VARCHAR(100);
	DECLARE @desc VARCHAR(500);

	SELECT @tableName=T.Name FROM Tables T WHERE T.TableID=@tableID;
	SELECT @testName=T.Name FROM Tests T WHERE T.TestID=@testID;
	SET @startTime=GETDATE();
	EXEC @testName @noRows, @tableName
	SET @endTime=GETDATE();

	SET @desc=@testName+' '+CONVERT(VARCHAR(10), @noRows)+' ROWS IN TABLE '+@tableName;
	INSERT INTO TestRuns VALUES (@desc, @startTime, @endTime);
	SET @testID=(SELECT MAX(TestRunID) FROM TestRuns);
	INSERT INTO TestRunTables VALUES (@testID, @tableID, @startTime, @endTime);
GO

EXEC runTests Studios, Movie, MoviesAndDirector, ViewStudios, 
ViewMoviesWithRespectivStudios, ViewMovieWithRespectivDirectors, 
1000, deleteRows, insertRows;

SELECT * FROM SYS.tables;