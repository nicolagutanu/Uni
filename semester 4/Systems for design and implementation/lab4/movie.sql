use [Movie_sdi]
go

create table Movie(
	id int primary key,
	name varchar(100),
	rating float)

create table Cinema(
	id int primary key,
	name varchar(100),
	nrOfSeats int)

create table Genre(
	id int primary key,
	name varchar(100))

create table Catalog(
	id int primary key,
	idMovie int references Movie(id),
	idGenre int references Genre(id))

create table Ticket(
	id int primary key,
	idMovie int references Movie(id),
	idCinema int references Cinema(id))

insert into Movie values (1, 'a', 1.0),
(2, 'b', 2.0),
(3, 'c', 3.0)

insert into Cinema values (1, 'x', 1),
(2, 'y', 2),
(3, 'z', 3)

insert into Genre values (1, 'm'),
(2, 'n'),
(3, 'o')

select * from Movie
select * from Cinema
select * from Genre

insert into Catalog values (1, 1, 1),
(2, 1, 3),
(3, 2, 3),
(4, 3, 1),
(5, 3, 2)

insert into Ticket values (1, 1, 1),
(2, 1, 2),
(3, 2, 2),
(4, 3, 2),
(5, 3, 3)

select * from Catalog
select * from Ticket