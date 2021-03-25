%math model:
%cond(l1l2..ln)= 1,l=[]
%                l1*cond(l2l3..ln), otherwise
%cond(L:list,S:resulting product)
%flow model:(i,o)
cond([],1).
cond([H|T],S1):-cond(T,S),S1 is H*S.

%math model:
%inst(e,l1l2..ln)= e U l1l2..ln
%                  l1 U el2..ln
%inst(E:element to be inserted,L:list,R:resulting list after insert)
%flow model:(i,i,o)
inst2(E,L,[E|L]).
inst2(E,[H|T],[H|R]):-inst2(E,T,R).

%math model:
%arrang(l1l2..ln,k)= [],k=0
%                    inst(l1,arrang(l2l3..ln,k-1),otherwise
%arrang(L:list of int nr,K:length of arrangement,R:resulting arrang)
%flow model:(i,i,o)
arrang(_,0,[]).
arrang(L,K,[H|T]):-K>0,K1 is K-1,inst2(H,Rest,L),arrang(Rest,K1,T).

%wrapper:
% wrapper_arrang(L:list of int nr,K:length of
% arrang,P:product of list elems,R:resulting arrang)
wrapper_arrang(L,K,P,R):-arrang(L,K,R),cond(R,S),S=:=P.

arrang_findall(L,K,P,R):-findall(RP,wrapper_arrang(L,K,P,RP),R).





comb([H|_],1,[H]).
comb([_|T],K,C):-comb(T,K,C).
comb([H|T],K,[H|C]):-K>1,K1 is K-1,comb(T,K1,C).
