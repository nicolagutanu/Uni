function pb6(n)
  a=0;
  b=0.5;
  erf=@(t)(2/sqrt(pi)).*(exp(-t.^2));
  h=(b-a)/n;
  x=[];
  for i= 0:n
    x=[x a+i*h];
  endfor
  x1=x(1:n);
  x2=x(2:n+1);
  x3=x(2:n);
  approx=((b-a)/(6*n)).*(erf(a)+erf(b)+4*sum(erf((x1+x2)/2))+2*sum(erf(x3)))
  
  accuracy=(approx*100)/0.520499876
endfunction