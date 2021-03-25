use [Cakes]
go

create table Chefs(
	idChef int primary key,
	name varchar(100),
	gender varchar(20),
	dateOfBirth date
)

create table CakeType(
	idType int primary key,
	name varchar(100),
	description varchar(200)
)

create table Cakes(
	idCake int primary key,
	name varchar(100),
	shape varchar(100),
	weight int,
	price int,
	idType int references CakeType(idType)
)

create table ChefsAndCakes(
	idChef int references Chefs(idChef),
	idCake int references Cakes(idCake),
	primary key(idChef, idCake)
)

create table Orders(
	idOrder int primary key,
	dateOfOrder date
)

create table OrdersAndCakes(
	idOrder int references Orders(idOrder),
	idCake int references Cakes(idCake),
	amount int,
	primary key(idOrder, idCake)
)


--2
create or alter procedure addCakeToOrder
	@idOrder int,
	@cakeName varchar(100),
	@P int
as
	if not exists(select * from Orders where idOrder=@idOrder)
	begin
		raiserror('nonexistent order', 16, 1)
		return
	end

	if not exists(select * from Cakes where name=@cakeName)
	begin
		raiserror('nonexistent cake', 16, 1)
		return
	end

	declare @idCake int
	set @idCake=(select idCake from Cakes where name=@cakeName)
	if exists(select * from OrdersAndCakes where idCake=@idCake and idOrder=@idOrder)
	begin
		update OrdersAndCakes
		set amount=@P
		where idCake=@idCake and idOrder=@idOrder
	end
	else
		insert into OrdersAndCakes values (@idOrder, @idCake, @P)
go


--3
create or alter function listChefNames()
returns table
return
	select name from Chefs C
	where not exists
		(select idCake from Cakes
		except
		select idCake from ChefsAndCakes
		where idChef=C.idChef)
go


insert into Chefs values
(1, 'a', 'm', '2000-12-09'),
(2, 'b', 'f', '2001-12-09'),
(3, 'c', 'm', '1999-12-09')

insert into CakeType values
(1, 'x', 'xxx'),
(2, 'y', 'yyy'),
(3, 'z', 'zzz')

insert into Cakes values
(1, 'g', 'gg', 1, 12, 1),
(2, 'h', 'hh', 1, 12, 2),
(3, 'i', 'ii', 1, 12, 2),
(4, 'j', 'jj', 1, 12, 3),
(5, 'k', 'kk', 1, 12, 1)

insert into ChefsAndCakes values
(1, 1),
(1, 2),
(3, 4),
(1, 3),
(1, 4),
(2, 5),
(1, 5),
(2, 1)

insert into Orders values
(1, '2020-12-01'),
(2, '2020-12-01'),
(3, '2020-12-01'),
(4, '2020-12-01')

exec addCakeToOrder 1, 'g', 2
exec addCakeToOrder 1, 'g', 4
exec addCakeToOrder 2, 'h', 2
exec addCakeToOrder 1, 'h', 2
exec addCakeToOrder 4, 'k', 5

select * from OrdersAndCakes

select * from listChefNames()