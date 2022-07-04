function pb3
  a=1;
  b=3;
  eps=10.^(-4);
  adaptive_simpsons(a,b,eps)
  n50=repeated_simpsons(50)
  n100=repeated_simpsons(100)
endfunction

function r=adaptive_simpsons(a,b,eps)
  h=(a+b)/2;
  r1=simpsons(a,b);
  r2=simpsons(a,h)+simpsons(h,b);
  if abs(r1-r2)<15*eps
    r=r2;
    return;
   else
    r=adaptive_simpsons(a,h,eps/2)+adaptive_simpsons(h,b,eps/2);
  endif
  f=@(x)(100./x.^2).*sin(10./x);
  x=[1:0.01:3];
  plot(x,f(x));
endfunction

function r=simpsons(a,b)
  f=@(x)(100./x.^2).*sin(10./x);
  h=(a+b)/2;
  r=((b-a)/6)*(f(a)+4*f(h)+f(b));
endfunction

function r=repeated_simpsons(n)
  a=1;
  b=3;
  f=@(x)(100./x.^2).*sin(10./x);
  h=(b-a)/n;
  x=[];
  for i= 0:n
    x=[x a+i*h];
  endfor
  x1=x(1:n);
  x2=x(2:n+1);
  x3=x(2:n);
  r=((b-a)/(6*n)).*(f(a)+f(b)+4*sum(f((x1+x2)/2))+2*sum(f(x3)));
endfunction