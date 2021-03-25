USE [Cinema]
GO

CREATE OR ALTER PROCEDURE deleteRows 
	@noRows INT, 
	@table VARCHAR(100)
AS
	IF @noRows<1
		RAISERROR('INVALID NUMBER OF ROWS', 10,1);
	DECLARE @lastRow INT;

	--DECLARE @query NVARCHAR(1000);
	--SET @query='SET '+CONVERT(VARCHAR(10), @lastRow)+'=(SELECT MAX(id) FROM '+@table+')-'+CONVERT(VARCHAR(10),@noRows)+';
	--	        DELETE FROM '+@table+' WHERE id>'+CONVERT(VARCHAR(10),@lastRow)+';'
	--EXECUTE sp_executesql @query;

	IF @table='MoviesAndDirector'
	BEGIN
		SET @lastRow=(SELECT MAX(id) FROM MoviesAndDirector)-@noRows;
		DELETE FROM MoviesAndDirector WHERE id>@lastRow;
	END

	IF @table='Movie'
	BEGIN
		SET @lastRow=(SELECT MAX(id) FROM Movie)-@noRows;
		DELETE FROM Movie WHERE id>@lastRow;
	END

	IF @table='Studios'
	BEGIN
		SET @lastRow=(SELECT MAX(id) FROM Studios)-@noRows;
		DELETE FROM Studios WHERE id>@lastRow;
	END
GO

CREATE OR ALTER PROCEDURE deleteAllRows @table VARCHAR(100)
AS
	DECLARE @query NVARCHAR(200);
	SET @query='DELETE FROM '+@table;
	EXECUTE sp_executesql @query;
GO