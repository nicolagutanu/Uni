%math model:
%cond(l1l2..ln)= false, abs(l1-l2)>3
%                cond(l2l3..ln), otherwise
%cond(L:list)
%flow model(i)
cond([_]).
cond([H1|[H2|T]]):-D is abs(H1-H2), D=<3,cond([H2|T]).

%math model:
%inst(e,l1l2..ln)= e U l1l2..ln
%                  l1 U el2..ln
%inst(E:element to be inserted,L:list,R:resulting list after insert)
%flow model:(i,i,o)
inst(E,L,[E|L]).
inst(E,[H|T],[H|R]):-inst(E,T,R).

%math model:
%perm(l1l2..ln)= [], l=[]
%                inst(l1,perm(l2l3..ln)), otherwise
%perm(L:list of int numbers,R:resulting permutation)
%flow model(i,o)
perm([],[]).
perm([H|T],R):-perm(T,S),inst(H,S,R).

%wrapper:
%wraper_perm(L:list,R:resulting permutations)
wrapper_perm(L,R):-perm(L,R),cond(R).

perm_findall(L,R):-findall(RP,wrapper_perm(L,RP),R).
