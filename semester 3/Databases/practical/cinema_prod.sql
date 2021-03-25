use [Cinema_prod]
go

create table Company(
	idCompany int primary key,
	name varchar(100)
)

create table Director(
	idDirector int primary key,
	name varchar(100),
	awards int
)

create table Movie(
	idMovie int primary key,
	name varchar(100),
	releaseDate date,
	idCompany int references Company(idCompany),
	idDirector int references Director(idDirector)
)

create table Actor(
	idActor int primary key,
	name varchar(100),
	ranking int
)

create table CinemaProductions(
	idProd int primary key,
	title varchar(100),
	idMovie int references Movie(idMovie)
)

create table ProductionAndActors(
	idActor int references Actor(idActor),
	idProd int references CinemaProductions(idProd),
	primary key(idActor, idProd)
)

alter table ProductionAndActors
add entryMoment time


--2
create or alter procedure addProductionAndActors
	@nameActor varchar(100),
	@entryMoment time, 
	@titleProd varchar(100)
as
	if not exists(select * from Actor where name=@nameActor)
	begin
		raiserror('nonexistent actor', 16, 1)
		return
	end

	if not exists(select * from CinemaProductions where title=@titleProd)
	begin
		raiserror('nonexistent cinema production', 16, 1)
		return
	end

	declare @idActor int, @idProd int
	set @idActor=(select idActor from Actor where name=@nameActor)
	set @idProd=(select idProd from CinemaProductions where title=@titleProd)

	insert into ProductionAndActors values (@idActor, @idProd, @entryMoment)
go


--3
create or alter view viewActors
as
	select A.name from Actor A
	where not exists
		(select idProd from CinemaProductions
		except
		select idProd from ProductionAndActors
		where A.idActor=idActor)
go


--4
create or alter function getAllMovies(@p int)
returns table
return
	select M.name from Movie M
	where releaseDate>='2018-01-01' and M.idMovie in
		(select idMovie from CinemaProductions CP
		group by idMovie
		having count(*)>=@p)
go

insert into Company values
(1, 'x'),
(2, 'y'),
(3, 'z')

insert into Director values
(1, 'a', 5),
(2, 'b', 1),
(3, 'c', 10)

insert into Movie values 
(1, 'aaa', '2018-01-28', 1, 1),
(2, 'bbb', '2019-10-18', 2, 1),
(3, 'ccc', '2017-01-17', 3, 2),
(4, 'ddd', '2016-01-30', 3, 3),
(5, 'eee', '2020-11-08', 1, 2)

insert into Actor values
(1, 'gg', 90),
(2, 'hh', 21),
(3, 'ii', 8),
(4, 'jj', 129)

delete from CinemaProductions

insert into CinemaProductions values
(1, 'dont matter1', 1),
(2, 'dont matter2', 1),
(3, 'dont matter3', 2),
(4, 'dont matter4', 3),
(5, 'dont matter5', 1),
(6, 'dont matter6', 4),
(7, 'dont matter7', 5)

exec addProductionAndActors 'gg', '09:10:00', 'dont matter1'
exec addProductionAndActors 'gg', '09:10:00', 'dont matter2'
exec addProductionAndActors 'gg', '09:10:00', 'dont matter3'
exec addProductionAndActors 'gg', '09:10:00', 'dont matter4'
exec addProductionAndActors 'gg', '09:10:00', 'dont matter5'
exec addProductionAndActors 'gg', '09:10:00', 'dont matter6'
exec addProductionAndActors 'gg', '09:10:00', 'dont matter7'
select * from ProductionAndActors

select * from getAllMovies(2)

select * from viewActors