%math model:
%instPower(l1l2..ln,k,n,e)= [],l=[]
%                           e U instPower(l1l2..ln,k+1,n*2,e),k=n+1
%                           instPower(l1l2..ln,k+1,n,e),otherwise
% instPower(L:list,K:length,N:pos to insert into,E:elem to
% insert,R:resulting list)
% flow model:(i,i,i,i,o)
instPower([],_,_,_,[]).
instPower([H|T],K,N,E,Rez):-K=:=N+1,!,K1 is K+1,N1 is N*2,
    instPower(T,K1,N1,E,R),Rez=[H|[E|R]].
instPower([H|T],K,N,E,[H|R]):-K1 is K+1,instPower(T,K1,N,E,R).




%math model:
%candidate(n)= n
%              candidate(n-1), n>1
%candidate(N:element,R:candidate for perm)
%flow model:(i,o)
candidate8(N,N).
candidate8(N,R):-N>1,N1 is N-1,candidate8(N1,R).

%math model:
%mem(e,l1l2..ln)= true,l1=e
%                 mem(e,l2l3..ln), otherwise
%mem(E:element,L:list)
%flow model:(i,i)
mem8(E,[E|_]).
mem8(E,[_|R]):-mem8(E,R).

%math model:
%perm_aux(n,l,k,r)= r=l, k=n
%                   perm_aux(n,candidate(n) U l,k+1,r),
%                 abs(l1-candidate(n))<=2 and member(candidate(n))=false
% perm_aux(N:element,L:collecting list,K:length of L,R:resulting perm)
% flow model:(i,i,i,o)
perm_aux(N,C,N,C):-!.
perm_aux(N,[H|T],K,L):-candidate8(N,I),abs(H-I)=<2,not(mem8(I,[H|T])),
    K1 is K+1,perm_aux(N,[I|[H|T]],K1,L).


%wrapper function:
%perm(N:element,R:resuling permutation)
perm(N,R):-candidate8(N,I),perm_aux(N,[I],1,R).

perm_findall(N,R):-findall(RP,perm(N,RP),R).
