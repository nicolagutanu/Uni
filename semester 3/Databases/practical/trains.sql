use [Trains]
go

create table TrainTypes(
	idTrainType int primary key,
	name varchar(200),
	description varchar(200)
);

create table Trains(
	idTrain int primary key,
	name varchar(200),
	ipTrainType int references TrainTypes(idTrainType)
);

create table Stations(
	idStation int primary key,
	name varchar(200) unique
);

create table Routes(
	idRoute int primary key,
	name varchar(200) unique,
	idTrain int references Trains(idTrain)
);

create table RoutesAndStations(
	id int identity primary key,
	idRoute int references Routes(idRoute),
	idStation int references Stations(idStation),
	arrival time,
	departure time
);

--2
create or alter procedure addStationToRoute
	@rName varchar(200), 
	@sName varchar(200), 
	@arr time, 
	@dep time
as
	declare @idS int, @idR int;

	if not exists(select * from Stations S where S.name=@sName)
	begin
		raiserror('invalid station name', 16, 1)
		return 
	end

	if not exists(select * from Routes R where R.name=@rName)
	begin
		raiserror('invalid route name', 16, 1)
		return 
	end

	set @idR=(select R.idRoute from Routes R where R.name=@rName)
	set @idS=(select S.idStation from Stations S where S.name=@sName)

	if exists(select * from RoutesAndStations RS where RS.idRoute=@idR and RS.idStation=@idS)
	begin
		update RoutesAndStations
		set arrival=@arr, departure=@dep
		where idStation=@idS and idRoute=@idR
	end
	else
	begin
		insert into RoutesAndStations values(@idR, @idS, @arr, @dep)
	end
go

--3
create or alter view passThrough as
	select R.name from Routes R 
	where not exists
		(select idStation from Stations
		except 
		select idStation from RoutesAndStations where R.idRoute=idRoute)
go

--4
create or alter function filter(@R int)
returns table
return
	select S.name from Stations S
	where S.idStation in
		(select RS.idStation from RoutesAndStations RS
		group by RS.idStation
		having count(*)>@R)
go