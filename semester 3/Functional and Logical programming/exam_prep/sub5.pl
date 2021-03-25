%math model:
%revers_help(l1l2..ln,p)= p=[],l=[]
%                         revers_help(l2l3..ln,p U revers_help(l1,[])),
%                         is_list(l1)=true
%                         revers_help(l2l3..ln,p U l1), otherwise
%revers_help(L:list,T:collector,R:reversed list)
%flow model(i,i,o)
revers_help([],R,R).
revers_help([X|Xs],T,R):-is_list(X),!,revers_help(X,[],Ys),revers_help(Xs,[Ys|T],R).
revers_help([X|Xs],T,R):-revers_help(Xs,[X|T],R).

%wrapper:
%revers(L:list,R:reversed list)
revers(Xs,Ys):-revers_help(Xs,[],Ys).


%math model:
%subst(l1l2..ln)= [],l=[]
%                 l1+subst(l2l3..ln),n>0
%                 subst(l2l3..ln),n>0
%subst(L:list of int nr,R:resulting subset)
%flow model:(i,o)
subst5([], []).
subst5([E|T], [E|NT]):-subst5(T, NT).
subst5([_|T], NT):-subst5(T, NT).

%math model:
%cond(l1l2..ln)= 0,l=[]
%                1+cond(l2l3..ln),otherwise
%cond(L:list,N:length of list)
%flow model:(i,o)
cond5([],0).
cond5([_|T],N1):-cond5(T,N),N1 is N+1.

%wrapper function:
%wrapper_subst(L:list of int nr,N:length,R:resulting subset)
wrapper_subst5(L,N,R):-subst5(L,R),cond5(R,LEN),LEN=:=N.

subst_findall5(L,N,R):-findall(RP,wrapper_subst5(L,N,RP),R).

