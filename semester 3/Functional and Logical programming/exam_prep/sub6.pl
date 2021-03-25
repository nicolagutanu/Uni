% math model:
%count(l1l2...ln,elem)= 0, l=[]
%                       1+count(l2l3...ln,elem), l1=elem
%                       count(l2l3...ln,elem), otherwise
%count(L:list,Elem:int,R:count)
%flow model:(i,i,o)
count([],_Elem,0).
count([H|T],H,R):-count(T,H,R1), R is R1+1.
count([H|T],Elem,R):-not(H=:=Elem),count(T,Elem,R).

%math model:
%delete(l1l2..ln)= [], l=[]
%                  delete(l2l3..ln), count(l1l2...ln,l1)>1
%                  [l1] U delete(l2l3...ln), otherwise
%delete(L:list, R:list)
%flow model:(i,o)
delete([],[]).
delete([H|T],[H|R]):-count([H|T],H,C), C=:=1, delete(T,R).
delete([H|T],R):-count([H|T],H,C), not(C=:=1), delete(T,R).




%math model:
%mem(e,l1l2..ln)= true,l1=e
%                 mem(e,l2l3..ln), otherwise
%mem(E:element,L:list)
%flow model:(i,i)
mem(E,[E|_]).
mem(E,[_|R]):-mem(E,R).

%math model:
%set(l1l2..ln)= [], l=[]
%               set(l2l3..ln), mem(l1)=true
%               l1 U set(l2l3..ln), otherwise
%set(L:list,P:resulting set)
%flow model:(i,o)
set([], []).
set([H|T], X):- mem(H, T), !, set(T, X).
set([H|T], [H|X]):- set(T, X).



%math model:
%inst(e,l1l2..ln)= e U l1l2..ln
%                  l1 U inst(e,l2l3..ln)
%inst(E:element to be inserted,L:list,R:resulting list)
%flow model:(i,i,o)
inst6(E,L,[E|L]).
inst6(E,[H|T],[H|R]):-inst6(E,T,R).

%math model:
%arrang(l1l2..ln,k)= [], l=[]
%                    inst(l1,arrang(l2l3..ln,k-1)), k>0
%arrang(L:list,N:length,R:resulting arrangement)
%flow model:(i,i,o)
arrang6(_,0,[]).
arrang6(L,N,[H|T]):-N>0,N1 is N-1,inst6(H,Rest,L),arrang6(Rest,N1,T).

%math model:
%cond(l1l2..ln)= 1,l=[]
%                l1*cond(l2l3..ln), otherwise
%cond(L:list,S:resulting product)
cond6([],1).
cond6([H|T],S1):-cond6(T,S),S1 is H*S.

%wrapper:
%wrapper_arrang(L:list of int nr,K:length of
%arrang,P:product of list elems,R:resulting arrang)
wrapper_arrang6(L,K,P,R):-arrang6(L,K,R),cond6(R,S),S=:=P.

arrang_findall6(L,K,P,R):-findall(RP,wrapper_arrang6(L,K,P,RP),R).
