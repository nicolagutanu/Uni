use [Practic]
go

create table Users(
	idUser int primary key,
	name varchar(100),
	penName varchar(100),
	yearOfBirth int
)

create table Topics(
	idTopic int primary key,
	name varchar(100),
	description varchar(200)
)

create table Competitons(
	idComp int primary key,
	yearOfComp int,
	weekNr int,
	idTopic int references Topics(idTopic)
	--it says that a competition has A topic, therefore we keep the id of the topic as a foreign key->we have a one(topic) to many(competitions) relationship
)

create table Poems(
	idPoem int primary key,
	idUser int references Users(idUser),
	--it says that a poem is written by a user, but that a user can write more than one poem so we keep the id of the user as a foreign key
	--we have a one(user) to many(poems) relationship
	title varchar(100),
	textPoem varchar(200)
)

create table PoemsAndComp(
	idPoem int references Poems(idPoem),
	idComp int references Competitons(idComp),
	primary key(idPoem, idComp)
	--it says that a user can submit several poems to a competition and it is obvious then that a competiton can have more then one poem
	--so we have a many(competitons) to many(poems) relationship
	--also, there is no point/also probably not allowed to enter the same poem twice in the same competiton, therefore the (idPoem, idComp) pair will be unique, so they can be the tuple primary key
)

create table Judge(
	idJudge int primary key,
	name varchar(100)
)

create table JudgeAndPoems(
	idJudge int references Judge(idJudge),
	idPoem int references Poems(idPoem),
	points int,
	primary key(idJudge, idPoem)
	--it says that a judge can evaluate any number of poems and a poem can then probably be evaluate by any number of jdges-> therefore we have
	--a many(judges) to many(poems) relationship
	--and once a judge has evaluate a poem, there is no point in reevaluating it, the tuple (idJudge, idPoem) is unique, so can become the primary key
)


--b
create or alter procedure addPoemToCompetiton
	@idPoem int,
	@idComp int
as
	if not exists(select * from Competitons where idComp=@idComp)
	begin
		raiserror('nonexistent competition', 16,1)
		return
	end

	if not exists(select * from Poems where idPoem=@idPoem)
	begin
		raiserror('nonexistent poem', 16,1)
		return
	end

	if exists(select * from PoemsAndComp where idComp=@idComp and @idPoem=idPoem)
	begin
		raiserror('poem already associated with the competition',16,1)
		return
	end

	insert into PoemsAndComp values (@idPoem, @idComp)
go


--c
create or alter view viewNamesOfUserWithFifteenPoems
as
	select U.name from Users U
	where U.idUser in
		(select P.idUser from Poems P
		group by P.idUser
		having count(*)>=15)
go

--d
create or alter function listJudgesWithAtLeastPCompetitons(@P int)
returns table
return
	select J.name from Judge J
	where J.idJudge in
		(select JP.idJudge from JudgeAndPoems JP
		group by JP.idJudge
		having count(*)>=@P)
go

insert into Users values
(1, 'x', 'xx', 2000),
(2, 'y', 'yy', 1999),
(3, 'z', 'zz', 1992)

insert into Topics values
(1, 'a', 'aaa'),
(2, 'b', 'bbb'),
(3, 'c', 'ccc')

insert into Competitons values
(1, 2018, 14, 1),
(2, 2017, 13, 2),
(3, 2008, 25, 2),
(4, 2009, 13, 1),
(5, 2018, 2, 3)

insert into Poems values
(1, 1, 'g', 'ggg'),
(2, 1, 'h', 'hhh'),
(3, 2, 'i', 'iii'),
(4, 2, 'k', 'kkk'),
(5, 2, 'j', 'jjj'),
(6, 3, 'l', 'lll')

insert into Judge values
(1, 'j1'),
(2, 'j2'),
(3, 'j3')

insert into JudgeAndPoems values
(1, 1, 2),
(2, 1, 5),
(1, 2, 7),
(1, 5, 8),
(3, 6, 5)

exec addPoemToCompetiton 1, 5
select * from PoemsAndComp
exec addPoemToCompetiton 2, 5
exec addPoemToCompetiton 6, 2
exec addPoemToCompetiton 1, 5 --error, already exists
exec addPoemToCompetiton 7, 5 --error, poem doesn't exist
exec addPoemToCompetiton 1, 7 --error, competition doesn't exist

select * from viewNamesOfUserWithFifteenPoems

select * from listJudgesWithAtLeastPCompetitons(3)