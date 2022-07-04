create or alter procedure insertTrialRun (
	@nameMovie varchar(400),
	@rating real,
	@nameDirector varchar(200),
	@nrOfMovies int) as
begin
	begin try
		begin transaction addMovie
			insert into Movie values (@nameMovie, @rating)
	end try
	begin catch
		if @@TRANCOUNT > 0
			rollback transaction
		print('insert failed - movie')
		return
	end catch

	begin try
		begin transaction addDirector
			insert into Director values (@nameDirector, @nrOfMovies)
	end try
	begin catch
		if @@TRANCOUNT > 0
			rollback transaction
		print('Insert failed - director')
		return
	end catch

	begin try
		declare @idMovie int = (select top 1 id from Movie order by id desc)
		declare @idDirector int = (select top 1 id from Director order by id desc)
		begin transaction addMD
			insert into MoviesAndDirector values (@idMovie, @idDirector)
		commit transaction addMD
		commit transaction addMovie
		commit transaction addDirector
	end try
	begin catch
		if @@TRANCOUNT > 0
			rollback transaction
		print('Insert failed - movies and director')
		return
	end catch

	print('Insert succeded')
end
go

exec insertTrialRun ALADDIN, 9.2, LUMPENS, 7

delete from Movie where Name='ALADDIN'
delete from Director where Name='LUMPENS'

select * from Movie
select * from Director
select * from MoviesAndDirector


