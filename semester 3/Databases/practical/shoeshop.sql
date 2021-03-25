use [ShoeShop]
go

--1
create table Shops(
	idShop int primary key,
	name varchar(200),
	city varchar(200)
);

create table Women(
	idWoman int primary key,
	name varchar(200),
	amountToSpend int
);

create table ShoeModels(
	idShoeModel int primary key,
	name varchar(200),
	season varchar(200)
);

create table Shoes(
	idShoe int primary key,
	price int,
	idShoeModel int references ShoeModels(idShoeModel)
);

create table ShoesAndShops(
	idShoe int references Shoes(idShoe),
	idShop int references Shops(idShop),
	nrShoes int,
	primary key (idShoe, idShop) 
);

create table WomanAndShoes(
	idWoman int references Women(idWoman),
	idShoe int references Shoes(idSHoe),
	shoesBought int,
	amountSpent int,
	primary key (idWoman, idShoe)
);


--2
create or alter procedure addShoeToShop
	@idShoe int,
	@shopName varchar(200),
	@nrShoes int
as
	declare @idShop int
	if not exists(select * from Shoes where @idShoe=idShoe)
	begin
		raiserror('nonexistent shoe', 16,1)
		return
	end

	if not exists(select * from Shops where @shopName=name)
	begin
		raiserror('nonexistent shop', 16, 1)
		return
	end
	set @idShop=(select S.idShop from Shops S where S.name=@shopName)

	insert into ShoesAndShops values(@idShoe, @idShop, @nrShoes)
go


--3 
create or alter view getWomen
as
	select name from Women
	where idWoman in
		(select idWoman from WomanAndShoes
		where shoesBought>=2)
go


--4
create or alter function listShoes(@T int)
returns table
return
	select S.idShoe from Shoes S
	where S.idShoe in 
		(select SS.idShoe from ShoesAndShops SS
		group by SS.idShoe
		having count(SS.idShoe)>=@T)
go

insert into Shops values (1, 'x', 'xx'),
(2, 'y', 'yy'),
(3, 'z', 'zz');

insert into Women values (1, 'a', 240),
(2, 'b', 346),
(3, 'c', 140);

insert into ShoeModels values (1, 'g', 'gg'),
(2, 'h', 'hh'),
(3, 'i', 'ii');

insert into Shoes values (1, 25, 1),
(2, 100, 1),
(3, 60, 2),
(4, 170, 3),
(5, 125, 3);

insert into WomanAndShoes values (1, 1, 3, 75),
(2, 5, 1, 125),
(3, 4, 2, 340),
(2, 2, 1, 100);

select * from Shops;
select * from Women;
select * from ShoeModels;
select * from Shoes;
select * from WomanAndShoes;

exec addShoeToShop 2, 'x', 5
exec addShoeToShop 3, 'x', 3
exec addShoeToShop 5, 'y', 2
exec addShoeToShop 1, 'z', 5
exec addShoeToShop 2, 'y', 5
exec addShoeToShop 4, 'x', 5

select * from ShoesAndShops

select * from getWomen

select * from listShoes(2)