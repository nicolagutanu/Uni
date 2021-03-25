use [Bank]
go

--1
create table Customers(
	idCustomer int primary key,
	name varchar(200),
	dateOfBirth datetime
);

create table BankAccounts(
	idAcc int primary key,
	iban varchar(50),
	balance int,
	holder varchar(200),
	idCustomer int references Customers(idCustomer)
);

create table Cards(
	idCard int primary key,
	number varchar(20),
	cvv varchar(20),
	idAcc int references BankAccounts(idAcc)
);

create table Atms(
	idAtm int primary key,
	address varchar(200)
);

create table Transactions(
	idTransaction int identity primary key,
	idAtm int references Atms(idAtm),
	idCard int references Cards(idCard),
	sum int,
	timeTransaction datetime
);


--2
create or alter procedure deleteTransactions
	@cardNumber varchar(20)
as
	declare @idCard int
	if not exists(select * from Cards where number=@cardNumber)
	begin
		raiserror('nonexistent card', 16, 1)
		return
	end

	set @idCard=(select idCard from Cards where number=@cardNumber)
	if not exists(select * from Transactions where idCard=@idCard)
	begin
		raiserror('nonexistent transactions with given card', 16, 1)
		return
	end

	delete from Transactions where idCard=@idCard
go


--3
create or alter view getCardNumbers
as
	select number, cvv from Cards C
	where not exists
		(select idAtm from Atms
		except
		select idAtm from Transactions where C.idCard=idCard)
go


--4
create or alter function filter()
returns table
return
	select C.number, C.cvv from Cards C
	where C.idCard in
		(select T.idCard from Transactions T
		group by T.idCard
		having sum(T.sum)>=2000)
go

insert into Customers (idCustomer, name, dateOfBirth) values(1, 'x', '2008-11-11 13:23:44'),
(2, 'y', '2008-11-11 13:23:44'),
(3, 'z', '2008-11-11 13:23:44'),
(4, 't', '2008-11-11 13:23:44'),
(5, 'p', '2008-11-11 13:23:44');

insert into BankAccounts values
(1, 'RO09BCYP0000001234567890', 20000, 'x', 1),
(2, 'RO09BCYP00000016264567890', 36000, 'x', 1),
(3, 'RO09BCYP00000134567890', 9800, 't', 4),
(4, 'RO09BCYP000000123283838890', 982000, 'p', 5),
(5, 'RO09BCYP0000001234526390', 12000, 'z', 3);

insert into Cards values
(1, '5500 0000 0000 0004', '004', 2),
(2, '5500 0000 2400 0004', '004', 2),
(3, '5500 2300 0450 0004', '004', 5),
(4, '5500 0000 0000 0003', '003', 4),
(5, '5500 0000 2637 0004', '004', 3);

insert into Atms values 
(1, 'wwwwwww'),
(2, 'wwdejejewwwww'),
(3, 'wwwwwwsdefw'),
(4, 'wwwwdeewww'),
(5, 'wwwbhhfjfjwwfjjfjww');

insert into Transactions values
(1, 2, 200, '2008-11-11 13:23:44'),
(1, 4, 200, '2008-11-11 13:23:44'),
(3, 5, 2001, '2008-11-11 13:23:44'),
(3, 4, 200, '2008-11-11 13:23:44'),
(2, 4, 200, '2008-11-11 13:23:44'),
(3, 4, 200, '2008-11-11 13:23:44'),
(4, 2, 1400, '2008-11-11 13:23:44'),
(4, 4, 200, '2008-11-11 13:23:44'),
(2, 4, 800, '2008-11-11 13:23:44'),
(1, 4, 1200, '2008-11-11 13:23:44'),
(5, 4, 200, '2008-11-11 13:23:44'),
(5, 2, 2000, '2008-11-11 13:23:44');

insert into Transactions values
(4, 1, 1400, '2008-11-11 13:23:44'),
(4, 1, 1400, '2008-11-11 13:23:44');

exec deleteTransactions '5500 0000 0000 0004'

select * from getCardNumbers;

select * from filter()