create or alter procedure insertTrialRun2 (
	@nameMovie varchar(400),
	@rating real,
	@nameDirector varchar(200),
	@nrOfMovies int) as
begin
	begin try
		begin transaction addMovie
			insert into Movie values (@nameMovie, @rating)
		commit transaction addMovie
	end try
	begin catch
		rollback transaction addMovie
		print('insert failed - movie')
		return
	end catch

	begin try
		begin transaction addDirector
			insert into Director values (@nameDirector, @nrOfMovies)
		commit transaction addDirector
	end try
	begin catch
		rollback transaction addDirector
		print('Insert failed - director')
		return
	end catch

	begin try
		declare @idMovie int = (select top 1 id from Movie order by id desc)
		declare @idDirector int = (select top 1 id from Director order by id desc)
		begin transaction addMD
			insert into MoviesAndDirector values (@idMovie, @idDirector)
		commit transaction addMD
	end try
	begin catch
		rollback transaction addMD
		print('Insert failed - movies and director')
		return
	end catch

	print('Insert succeded')
end
go

exec insertTrialRun2 ALADDIN, 9.2, LUMPENS, 7

delete from Movie where Name='ALADDIN'
delete from Director where Name='LUMPENS'

select * from Movie
select * from Director
select * from MoviesAndDirector