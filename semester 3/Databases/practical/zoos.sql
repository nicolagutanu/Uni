use [Zoos]
go

create table Zoo(
	idZoo int primary key,
	admin varchar(100),
	name varchar(100)
)

create table Animal(
	idAnimal int primary key,
	name varchar(100),
	dateOfBirth date,
	idZoo int references Zoo(idZoo)
)

create table Foods(
	idFood int primary key,
	name varchar(100)
)

create table AnimalsAndFoods(
	idAnimal int references Animal(idAnimal),
	idFood int references Foods(idFood),
	quota int,
	primary key(idAnimal, idFood)
)

create table Visitor(
	idVisitor int primary key,
	name varchar(100),
	age int
)

create table Visits(
	idVisit int primary key,
	idVisitor int references Visitor(idVisitor),
	idZoo int references Zoo(idZoo),
	dayOfVisit varchar(20),
	price int
)


--2
create or alter procedure deleteFoodQuotas
	@animalName varchar(100)
as
	if not exists(select * from Animal where name=@animalName)
	begin
		raiserror('nonexistent animal', 16, 1)
		return
	end

	declare @idAnimal int
	set @idAnimal=(select idAnimal from Animal where name=@animalName)
	if not exists(select * from AnimalsAndFoods where idAnimal=@idAnimal)
	begin
		raiserror('no food quotas to be deleted', 16, 1)
		return
	end
	else
		delete from AnimalsAndFoods
		where @idAnimal=idAnimal
go

select idZoo from Visits
group by idZoo
having count(*)=
	(select top 1 count(idZoo) totalCount from Visits
	group by idZoo
	order by totalCount desc)

--3
create or alter view viewZoosWithLeastVisits
as
	select name from Zoo
	where idZoo in
		(select idZoo from Visits
		group by idZoo
		having count(*)=
			(select top 1 count(idZoo) totalCount from Visits
			group by idZoo
			order by totalCount))
go


--4
create or alter function listVisitors(@N int)
returns table
return
	select idVisitor from Visitor
	where idVisitor in
		(select idVisitor from Visits
		where idZoo in 
			(select idZoo from Animal
			group by idZoo
			having count(*)>=@N))
go

insert into Zoo values
(1, 'x', 'xxx'),
(2, 'y', 'yyy'),
(3, 'z', 'zzz')

insert into Animal values
(1, 'a', '2018-01-01', 1),
(2, 'b', '2018-01-01', 3),
(3, 'c', '2018-01-01', 2),
(4, 'd', '2018-01-01', 2),
(5, 'e', '2018-01-01', 1)

insert into Animal values
(6, 'f', '2018-01-01', 1)

insert into Foods values
(1, 'g'),
(2, 'h'),
(3, 'i')

insert into AnimalsAndFoods values
(1, 1, 10),
(2, 1, 20),
(5, 2, 9),
(4, 3, 8),
(1, 3, 12),
(3, 2, 20)

insert into Visitor values
(1, 'anny1', 20),
(2, 'anny2', 30),
(3, 'anny3', 68),
(4, 'anny4', 44)

insert into Visits values
(1, 1, 1, 't', 20),
(2, 2, 3, 't', 20),
(3, 3, 3, 't', 20),
(4, 2, 2, 't', 20)

exec deleteFoodQuotas 'a'
select * from AnimalsAndFoods

select * from viewZoosWithLeastVisits

select * from listVisitors(3)