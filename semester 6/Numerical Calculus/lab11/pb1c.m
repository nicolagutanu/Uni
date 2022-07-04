function pb1c
  A=[3 -1 0 0 0 0;
    -1 3 -1 0 0 0;
    0 -1 3 -1 0 0;
    0 0 -1 3 -1 0;
    0 0 0 -1 3 -1;
    0 0 0 0 -1 3];
    b=[2;1;1;1;1;2];
    [_,n]=size(A);
    x0=zeros(n,1);
    x1=rand(n,1);
    k=0;
    miu=0.7782;
    eps=10.^(-3);
    while (norm(x1-x0)>eps)
      k=k+1;
      x0=x1;
      x1=zeros(n,1);
      for i= 1:n
        x1(i,1)=(((b(i,1)-A(i,1:i-1)*x1(1:i-1,1)-A(i,i+1:n)*x0(i+1:n,1))*miu)/A(i,i))+(1-miu)*x0(i,1);
      endfor
    endwhile
    k
    x1
endfunction