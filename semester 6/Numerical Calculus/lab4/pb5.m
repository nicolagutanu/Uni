function r=pb5(x,f,v)
  n=length(x);
  mat=[f',NaN*zeros(n,n-1)];
  for j= 2:n
    for i= j:n
      mat(i,j)=((v-x(i)).*mat(i-1,j-1)-(v-x(i-j+1)).*mat(i,j-1))./(x(i)-x(i-j+1));
    endfor
  endfor
  r=mat(n,n);
endfunction