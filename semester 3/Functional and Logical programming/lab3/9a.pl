%For a list of integer number, write a predicate to add in list
%after 1-st, 3-rd, 7-th, 15-th element a given value e.

%math model:
%addEl(l1l2...ln, pos, elem): [], n=0
%                         [l1 elem] U addEl[l2l3...ln,pos+1,elem],
%                      pos=1,3,7,15
%                      addEl[l1l2...ln,pos+1,elem]
%flow model:(i,i,i,o)
%addEl(L1,Pos,E,R): L1-initial list
%                   Pos-current position in initial list
%                   E-given element to be added
%                   R-resulting list
addEl([],_,_,[]).
addEl([H|T],Pos,E,Result):-Pos=:=1,!,Pos1 is Pos+1,addEl(T,Pos1,E,R), Result=[H|[E|R]].
addEl([H|T],Pos,E,Result):-Pos=:=3,!,Pos1 is Pos+1,addEl(T,Pos1,E,R), Result=[H|[E|R]].
addEl([H|T],Pos,E,Result):-Pos=:=7,!,Pos1 is Pos+1,addEl(T,Pos1,E,R), Result=[H|[E|R]].
addEl([H|T],Pos,E,Result):-Pos=:=15,!,Pos1 is Pos+1,addEl(T,Pos1,E,R), Result=[H|[E|R]].
addEl([H|T],Pos,E,[H|R]):-Pos1 is Pos+1,addEl(T,Pos1,E,R).










