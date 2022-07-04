function pb2a
  A=[3 1 1;
    -2 4 0;
    -1 2 -6];
  D=diag(diag(A));
  L=tril(A,-1);
  U=triu(A,1);
  b=[12;2;-5];
  [_,n]=size(A);
  eps=10.^(-5);
  x0=zeros(n,1);
  x1=rand(n,1);
  while (norm(x1-x0)>eps)
    x0=x1;
    x1=-inv(D)*(L+U)*x0 + inv(D)*b;
  endwhile
  x1
endfunction