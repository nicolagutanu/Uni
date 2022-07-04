function r=pb4(v)
  x=[121,100,81];
  f=sqrt(x);
  n=length(x);
  mat=[f',NaN*zeros(n,n-1)];
  for j= 2:n
    for i= j:n
      mat(i,j)=(1/(x(i)-x(j-1))).*det([mat(j-1,j-1) x(j-1)-v;mat(i,j-1) x(i)-v]);
    endfor
  endfor
  r=mat(n,n);
endfunction