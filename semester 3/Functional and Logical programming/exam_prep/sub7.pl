%math model:
%del(l1l2..ln,n,k)= [],l=[]
%                   del(l2l3..ln,2*n,k+1),n=k
%                   l1 U del(l2l3..ln,n,k+1), otherwise
% del(L:list,N:position to be deleted from,K:lenght of L,R:resulting
% list)
% flow model:(i,i,i,o)
del([],_,_,[]).
del([_|T],N,K,R):-N=:=K,!,N1 is N*2,K1 is K+1,del(T,N1,K1,R).
del([H|T],N,K,[H|R]):-K1 is K+1,del(T,N,K1,R).

%wrapper function
%wrapper_del(L:list,N:position to be deleted from,R:resulting list)
wrapper_del(L,N,R):-del(L,N,1,R).



%math model:
%subst(l1l2..ln)= [],l=[]
%                 l1+subst(l2l3..ln),n>0
%                 subst(l2l3..ln),n>0
%subst(L:list of int nr,R:resulting subset)
%flow model:(i,o)
subst7([], []).
subst7([E|T], [E|NT]):-subst7(T, NT).
subst7([_|T], NT):-subst7(T, NT).

%math model:
%cond(l1l2..ln)= 0,l=[]
%                1+cond(l2l3..ln), otherwise
cond7([],0).
cond7([_|T],N1):-cond7(T,N),N1 is N+1.

%wrapper function:
%wrapper_subst(L:list of int nr,R:resulting subset)
wrapper_subst7(L,R):-subst7(L,R),cond7(R,LEN),mod(LEN,2)=:=0.

subst_findall7(L,R):-findall(RP,wrapper_subst7(L,RP),R).

