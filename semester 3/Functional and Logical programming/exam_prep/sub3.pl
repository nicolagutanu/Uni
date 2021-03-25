%math model:
%subst(l1l2..ln)= [],l=[]
%                 l1+subst(l2l3..ln),n>0
%                 subst(l2l3..ln),n>0
%subst(L:list of int nr,R:resulting subset)
%flow model:(i,o)
subst([], []).
subst([E|T], [E|NT]):-subst(T, NT).
subst([_|T], NT):-subst(T, NT).

%math model:
%cond(l1l2..ln,n,p)= n=p=0,l=[]
%                    cond(l2l3..ln,n+1,p+l1)
%cond(L:list of int nr,N:length of subset,P:sum of subset)
%flow model:(i,o,o)
cond([],0,0).
cond([H|T],N1,P1):-cond(T,N,P),N1 is N+1,P1 is P+H.

%wrapper function:
%wrapper_subst(L:list of int nr,N:min length,R:resulting subset)
wrapper_subst(L,N,R):-subst(L,R),cond(R,LEN,P),LEN>=N,mod(P,3)=:=0.

subst_findall(L,N,R):-findall(RP,wrapper_subst(L,N,RP),R).
