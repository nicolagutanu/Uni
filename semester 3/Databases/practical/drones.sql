use [Drones]
go

create table DroneManufacturer(
	idMan int primary key,
	name varchar(100)
);

create table DroneModel(
	idModel int primary key,
	name varchar(100),
	battery int,
	speed int,
	idMan int references DroneManufacturer(idMan)
);

create table Drone(
	idDrone int primary key,
	serialNr varchar(100),
	idModel int references DroneModel(idModel)
);

create table PizzaShop(
	name varchar(100) primary key,
	address varchar(100)
);

create table Customer(
	name varchar(100) primary key,
	loyaltyScore int
);

create table Delivery(
	idDelivery int identity primary key,
	customerName varchar(100) references Customer(name),
	shopName varchar(100) references PizzaShop(name),
	idDrone int references Drone(idDrone),
	deliveryTime datetime
);


--b
create or alter procedure addDelivery
	@customer varchar(100),
	@pizzaShop varchar(100),
	@drone varchar(100),
	@deliveryTime datetime
as
	if not exists(select * from Customer where @customer=name)
	begin
		raiserror('nonexistent customer', 16, 1)
		return
	end

	if not exists(select * from PizzaShop where @pizzaShop=name)
	begin
		raiserror('nonexistent pizza shop', 16, 1)
		return
	end

	if not exists(select * from Drone where @drone=serialNr)
	begin
		raiserror('nonexistent drone', 16, 1)
		return
	end
	
	declare @idDrone int
	set @idDrone=(select idDrone from Drone where @drone=serialNr)
	insert into Delivery values (@customer, @pizzaShop, @idDrone, @deliveryTime)
go


--c
insert into DroneManufacturer values
(1, 'x'),
(2, 'y'),
(3, 'z');

insert into DroneModel values 
(1, 'x1', 20, 100, 1),
(2, 'z2', 20, 95, 3),
(3, 'y1', 30, 100, 2),
(4, 'z1', 20, 100, 3),
(5, 'x2', 25, 80, 1);

insert into Drone values
(1, 'hdgg6ahdg7', 1),
(2, 'jkanejdn3233jjj', 5),
(3, 'sjjjf6hshs788', 2),
(4, 'bjkabejd3322', 5),
(5, 'nsndkn2234f', 3);

create or alter view getManufacturers
as
	select DM.name from DroneManufacturer DM
	where DM.idMan in
		(select DMO.idMan from DroneModel DMO
		where DMO.idModel in
			(select D.idModel from Drone D
			group by D.idModel
			having count(*)=
				(select top 1 count(idModel) totalCount from Drone
				group by idModel
				order by totalCount desc)))
go


--d
create or alter function listNamesOfCustomers(@D int)
returns table
return
	select C.name from Customer C
	where C.name in 
		(select D.customerName from Delivery D
		group by D.customerName
		having count(*)>=@D)
go

insert into PizzaShop values
('a', 'aaaa'),
('b', 'bbbb'),
('c', 'cccc');

insert into Customer values 
('g', 5),
('h', 7),
('i', 8);

exec addDelivery 'g', 'a', 'hdgg6ahdg7', '2008-11-11 13:23:44'
exec addDelivery 'h', 'b','sjjjf6hshs788', '2008-11-11 13:23:44'
exec addDelivery 'i', 'b', 'sjjjf6hshs788', '2008-11-11 13:23:44'
exec addDelivery 'g', 'a', 'hdgg6ahdg7', '2008-11-11 13:23:44'
exec addDelivery 'g', 'c','hdgg6ahdg7', '2008-11-11 13:23:44'

select * from listNamesOfCustomers(3)

select * from getManufacturers