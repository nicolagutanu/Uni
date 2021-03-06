% For a heterogeneous list, formed from integer numbers and list of numbers;
%add in every sublist after 1-st,3-rd, 7-th, 15-th element the value found
% before the sublist in the heterogenous list.The list has theparticularity
% that starts with a number and there aren?t two consecutive elementslists.
%math model:
%changeList(l1l2...ln)= [],n=0
%                       [l1l2] U changeList(l3l4...ln), not(is_list(l2))
%                       addEl(l1l2) U changeList(l3l4...ln), otherwise
%
%flow model:(i,o)
%changeList(L,R):L-initial list
%                R-resulting list
addEl([],_,_,[]).
addEl([H|T],Pos,E,Result):-Pos=:=1,!,Pos1 is Pos+1,addEl(T,Pos1,E,R), Result=[H|[E|R]].
addEl([H|T],Pos,E,Result):-Pos=:=3,!,Pos1 is Pos+1,addEl(T,Pos1,E,R), Result=[H|[E|R]].
addEl([H|T],Pos,E,Result):-Pos=:=7,!,Pos1 is Pos+1,addEl(T,Pos1,E,R), Result=[H|[E|R]].
addEl([H|T],Pos,E,Result):-Pos=:=15,!,Pos1 is Pos+1,addEl(T,Pos1,E,R), Result=[H|[E|R]].
addEl([H|T],Pos,E,[H|R]):-Pos1 is Pos+1,addEl(T,Pos1,E,R).


changeList([],[]).
changeList(H,H):-!.
changeList([H1|[H2|T]],Result):-is_list(H2),!,addEl(H2,1,H1,H3),changeList(T,R),Result=[H1|[H3|R]].
changeList([H1|[H2|T]],[H1|[H2|R]]):-not(is_list(H2)),!,changeList(T,R).

