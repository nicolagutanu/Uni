%7. A player wants to choose the predictions for 4 games. The
% predictions can be 1, X, 2. Write a predicate
% to generate all possible variants considering that: last
%prediction can’t be 2 and no more than two
%possible predictions X.

%so we break the problem into smaller pieces:
%1. we have a function that will generate our predictions one by
%one:
%math model:
%prediction(l1l2...ln,p)=  p=l1
%                          prediction(l2l3...ln)
%predition(L:list, P:int)
%flow model:(i, o)
prediction([H|_],H).
prediction([_|T],P):-prediction(T,P).

% then we need 2 functions that check the conditions
% first for the last to not be a 2
% math model:
% condition(l1l2l3l4)= false, l4=2
%                      true, otherwise
% condition1(L:list, N:int)
% flow model:(i,i)
condition1([],_).
condition1([H|T],N):-N=:=4, number(H),H=:=1,N1 is N+1,condition1(T,N1).
condition1([H|T],N):-N=:=4, H=x,N1 is N+1,condition1(T,N1).
condition1([_|T],N):-not(N=:=4), N1 is N+1, condition1(T,N1).


%and the second function to check that X doesn't appear more
%than twice, which is basically a count function
%count(l1l2...ln,elem,c)= true, c<=2
%                         count(l2l3...ln,elem,c+1), l1=elem
%                         count(l2l3...ln,elem,c), otherwise
%condition2(L:list,Elem:int,R:int)
%flow model:(i,i,i).
condition2([],_Elem,R):-R=<2.
condition2([H|T],H,R):- R1 is R+1,!,condition2(T,H,R1).
condition2([H|T],Elem,R):-not(H=Elem),condition2(T,Elem,R).

%and the big function that brings them all together:
%T-list with possibilities for predictions
%LR-is our result
%Len-holds length of result as is being built
%R:collector variable, builds the solution
%solution_help(T,LR,Len,R)= prediction(T) U R, Len<4
%                           LR, cond1=true and cond2=true and
%                           Len=4
%solution_help(T:list,LR:list,Len:int,R:list)
% flow model(i,o,i,i).
solution_help(_,[],4,R):-!,condition1(R,1), condition2(R,x,0).
solution_help(T,[P|LR],LEN,R):-prediction(T,P),
    LEN1 is LEN+1,
    solution_help(T,LR,LEN1,[P|R]).

%and a wrapper function to run the whole thing
solution(R):-solution_help([1,2,x],R,0,[]).








