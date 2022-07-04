function pb3
  A=[1 1 1 1; 2 3 1 5; -1 1 -5 3; 3 1 7 -2];
  b=[10;31;-2;18];
  [_,n]=size(A);
  for p= 1:n-1
    [m,q]=max(abs(A(p:n,p)));
    q=q+p-1;
    A([p q],:)=A([q p],:);
    aux=b(p,1);
    b(p,1)=b(q,1);
    b(q,1)=aux;
    for j= p+1:n
      b(j,1)=b(j,1)-(A(j,p)*b(p,1))/A(p,p);
      A(j,1:n)=A(j,1:n)-(A(j,p)*A(p,1:n))/A(p,p);
    endfor
  endfor
  x=[b(n,1)/A(n,n)];
  for i= n-1:-1:1
    k=1;
    t=0;
    for j= i+1:n
      t=x(k)*A(i,j)+t;
      k=k+1;
    endfor
    term=(b(i,1)-t)/A(i,i);
    x=[term x];
  endfor
  x
endfunction