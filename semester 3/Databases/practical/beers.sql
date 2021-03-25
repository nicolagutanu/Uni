use [Bars]
go

create table Bars(
	idBar int primary key,
	name varchar(100)
)

create table Customers(
	idCustomer int primary key,
	name varchar(100)
)

create table Categories(
	idCategory int primary key,
	name varchar(100)
)

create table Beers(
	idBeer int primary key,
	name varchar(100),
	price int,
	idCategory int references Categories(idCategory) 
	--it says that a beer can have only category, but that a category can have multiple beers->therefore we have a one(category) to many(beers) relationship 
)

create table BarsAndBeers(
	idBB int primary key,
	idBeer int references Beers(idBeer),
	idBar int references Bars(idBar),
	price int
	--it says that a bar can sell 0 or more beers and that a beer can be sold into 0 or more bars->therefore we have a many(bars) to many(beers) relationship
)

create table Orders(
	idOrder int primary key,
	idBB int references BarsAndBeers(idBB),
	idCustomer int references Customers(idCustomer),
	timestamp datetime
	--it says that customers buys beers(as in multiple) sold by bars(also multiple)->therefore we can keep the idBB which will lead to a beer that is sold in a bar and idCustomer
	--->we have a many(customers) to many(bars and beers) relationship
)

create table Favorites(
	idFavorite int primary key,
	idCustomer int references Customers(idCustomer),
	idBeer int references Beers(idBeer)
	--it says that a customer can have favorite beers(multiple) and obviously a beer can be favored by more than one customer->therefore a many(customers) to many(beers) relationship
)


--2
--da sincer ce?


--3
create or alter view viewExpensiveBars
as
	select distinct B.name from Bars B
	where B.idBar in
		(select idBar from BarsAndBeers BB
		inner join Beers Br On
		BB.idBeer=Br.idBeer and BB.price>=2*Br.price)
go

insert into Bars values
(1, 'x'),
(2, 'y'),
(3, 'z')

insert into Categories values
(1, 'cheap'),
(2, 'medium price'),
(3, 'expensive')

insert into Beers values
(1, 'a', 2, 1),
(2, 'b', 5, 3),
(3, 'c', 3, 2),
(4, 'd', 4, 2),
(5, 'e', 7, 3)

insert into BarsAndBeers values 
(1, 1, 1, 3),
(2, 3, 1, 7),
(3, 5, 2, 9),
(4, 4, 3, 10),
(5, 5, 3, 17)

select * from viewExpensiveBars