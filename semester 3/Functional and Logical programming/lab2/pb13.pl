%Transform a list in a set, in the order of the last occurrences
% 0f elements. Eg.: [1,2,3,1,2] is transformed in [3,1,2]
%
% to manage the last occurance part, I will do a function that
% counts the number of occurances of an element in the list and
% then delete that element if the fct return >1
% math model:
% count(l1l2...ln,elem)= 0, n=0
%                        1+count(l2l3...ln,elem), l1=elem
%                        count(l2l3...ln,elem), otherwise
% where Elem=the element we want to search through the list and
% R=the result, how many times the element appears in the list
%count(L:list, Elem:int, R:int)
%FlowModel:(i,i,o)
count([],_Elem,0).
count([H|T],H,R):-count(T,H,R1), R is R1+1.
count([H|T],Elem,R):-not(H=:=Elem),count(T,Elem,R).

%and to delete the elements we use:
%delete(l1l2..ln)= [], n=0
%                  delete(l2l3..ln), count(l1l2...ln,l1)>1
%                  [l1] U delete(l2l3...ln), otherwise
%R=the resulting list after the transformation
%delete(L1:list, R:list)
%FlowModel(i,o)
delete([],[]).
delete([H|T],[H|R]):-count([H|T],H,C), C=:=1, delete(T,R).
delete([H|T],R):-count([H|T],H,C), not(C=:=1), delete(T,R).
