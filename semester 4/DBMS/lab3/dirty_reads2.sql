--dirty reads
set transaction isolation level read committed
set transaction isolation level read uncommitted
select * from Studios 



--non-repeatable reads
update Studios
set nrOfMoviesPLanned = 20
where id = 1

select * from Studios


--phantom reads
insert into Studios values ('PARAMOUNT PICTURES', 18)

delete from Studios where Name='PARAMOUNT PICTURES'


--deadlock
	--a victim is chosen
	--the least expensive transaction is rolled back
begin transaction
	update Studios
	set nrOfMoviesPlanned=20
	where id=1

	update Movie 
	set Rating=6.9
	where id=1
commit transaction


--update conflict
set transaction isolation level snapshot
set LOCK_TIMEOUT 10000

begin transaction
	update Movie 
	set Rating=6.9
	where id=1
	waitfor delay '00:00:10'
commit transaction

SELECT resource_type, request_mode, request_type, request_status, request_session_id 
FROM sys.dm_tran_locks
WHERE request_owner_type = 'TRANSACTION'