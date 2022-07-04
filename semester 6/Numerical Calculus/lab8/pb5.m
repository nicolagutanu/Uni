function pb5(n)
  a=0;
  b=pi;
  f=@(x)1./(4+sin(20.*x));
  h=(b-a)/n;
  x=[];
  for i= 0:n
    x=[x a+i*h];
  endfor
  x1=x(1:n);
  x2=x(2:n+1);
  x3=x(2:n);
  approx=((b-a)/(6*n)).*(f(a)+f(b)+4*sum(f((x1+x2)/2))+2*sum(f(x3)))
endfunction