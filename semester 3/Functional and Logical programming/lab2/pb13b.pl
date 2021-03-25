% Define a predicate to determine the greatest common divisor of
% all numbers in a list
%we first need a function to determine greatest common divisor
% gcd(a, b)= a, a=b
%            gcd(a-b, b), a>b
%            gcd(a, b-a), otherwise
%gcd(A:int,B:int,R:int)
%FlowModel(i,i,o)
gcd(0,A,A):-A>0,!.
gcd(A,B,R):-A>=B,R1 is A-B, gcd(R1,B,R).
gcd(A,B,R):-B>A,R1 is B-A, gcd(R1,A,R).

%and now the function to determine the gcd of a list
%gcdList(l1l2...ln)= [], n=0
%                    l1, n=1
%                    gcd(l1,l2) U gcdList(l3l4...ln), otherwise
% gcdList(L:list, R:int)
% FlowModel(i,o)
gcdList([],R):-R is 0, !.
gcdList(H,H):-!.
gcdList([H1|[H2|T]],R):-gcd(H1,H2,Z),H1 is Z, gcdList([H2|T],R).
