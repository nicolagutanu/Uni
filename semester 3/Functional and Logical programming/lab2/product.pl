%initial list, compute the product of the elems with  a const k
%1 2 3 => k=2 => 2,4,6
%product(l:list, k:int, r:list)
%flowmodel(i,i,o)
product([],_,[]).
product(H,K,R):-H1 is H*K, product(H,K,H1).
product([H1|T],K,[H2|R]):-H2 is K*H1, product(T,K,R).
