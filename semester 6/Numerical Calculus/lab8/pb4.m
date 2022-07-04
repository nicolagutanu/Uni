function pb4
  a=1;
  b=2;
  eps=10.^(-3);
  fderiv=@(x)1./x;
  x=[a:0.1:b];
  m=max(fderiv(x));
  calc=sqrt((((b-a).^3)*m)/(12*eps));
  n=round(calc);
  if (n<=calc)
    n=n+1;
  endif
  n
  
  f=@(x)x.*log(x);
  h=(b-a)/n;
  x=[];
  for i= 1:n-1
    x=[x a+i*h];
  endfor
  approx=((b-a)/(2*n)).*(f(a)+f(b)+2.*sum(f(x)))
endfunction