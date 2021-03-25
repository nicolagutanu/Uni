%math model:
%inst(e,l1l2..ln)= e U l1l2..ln
%                  l1 U el2..ln
%inst(E:element to be inserted,L:list,R:resulting list after insert)
%flow model:(i,i,o)
inst4(E,L,[E|L]).
inst4(E,[H|T],[H|R]):-inst4(E,T,R).

%math model:
%arrang(l1l2..ln,k)= [],k=0
%                    inst(l1,arrang(l2l3..ln,k-1),otherwise
%arrang(L:list of int nr,K:length of arrangement,R:resulting arrang)
%flow model:(i,i,o)
arrang4(_,0,[]).
arrang4(L,N,[H|T]):-N>0,N1 is N-1,inst4(H,Rest,L),arrang4(Rest,N1,T).

%math model:
%cond(l1l2..ln)= 1,l=[]
%                l1*cond(l2l3..ln), otherwise
%cond(L:list,S:resulting product)
%flow model:(i,o)
cond4([],1).
cond4([H|T],S1):-cond4(T,S),S1 is H*S.

%wrapper:
% wrapper_arrang(L:list of int nr,K:length of
% arrang,P:product of list elems,R:resulting arrang)
wrapper_arrang4(L,K,V,R):-arrang4(L,K,R), cond4(R,S),S=<V.

arrang_findall(L,K,V,R):-findall(RP,wrapper_arrang4(L,K,V,RP),R).

