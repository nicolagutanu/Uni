begin transaction
	update Studios
	set nrOfMoviesPLanned = 5
	where id = 1

	update Studios
	set nrOfMoviesPLanned = 6
	where id = 2
commit transaction

--dirty reads
	--happens when a transaction reads uncommitted data
set transaction isolation level read committed --solved dirty reads 
	--S locks released as soon as the select operation is done
	--X locks released at the end of the transaction
begin transaction 
	update Studios
	set nrOfMoviesPLanned = 5
	where id = 1
waitfor delay '00:00:15'
rollback transaction


--non-repeatable reads
	--happens when the 2 read queries are execute in a transaction and they return different results
set transaction isolation level repeatable read --solved non-repeatable reads
	--holds S locks and X locks until the end of the transaction
begin transaction
	select * from Studios where id=1
	waitfor delay '00:00:15'
	select * from Studios where id=1
commit transaction


--phantom read
	--happens when 2 queries are executed in a transaction and they get different nr of rows
set transaction isolation level serializable --solved phantom reads
	--highest isolation level
	--range lock on the rows greater than 4
begin transaction
	select * from Studios where id>4
	waitfor delay '00:00:15'
	select * from Studios where id>4
commit transaction


--deadlock
	--happens when transactions want to lock data that is dependent on the first data to be unlocked
	--it basically creates of cycle of everyone is waiting for someone and no one is getting anything
	--simple fix, lock data in the transaction in the same order
select * from Movie
select * from Studios

begin transaction
	--has X lock since it tries to write data
	update Movie 
	set Rating=7
	where id=1

	--is requesting an X lock to try to write data, but this data is already locked by another
	--transaction
	update Studios
	set nrOfMoviesPlanned=21
	where id=1
commit transaction


--update conflict
	--happens when 2 transactions are trying to update/delete the same row
	--since there's no way for the database engine to know who should be executed in the end, it
	--had to kill a transaction

--snapshot
	--works on a snapshot of the dabase (a fraction of it, different versions are kept)
	--provides the most recent committed data
	--that's why we get a conflict when trying to update the same row twice, because the first
	--transaction was not yet committed
alter database Cinema_easier set ALLOW_SNAPSHOT_ISOLATION on

SELECT DB_NAME(database_id), 
    is_read_committed_snapshot_on,
    snapshot_isolation_state_desc 
from sys.databases
where database_id=DB_ID()

set transaction isolation level snapshot
--specify how long a transaction waits for a locked resource to be released
set LOCK_TIMEOUT 10000

begin transaction
	update Movie 
	set Rating=7
	where id=1
	waitfor delay '00:00:10'
commit transaction


SELECT resource_type, request_mode, request_type, request_status, request_session_id 
FROM sys.dm_tran_locks
WHERE request_owner_type = 'TRANSACTION'