function pb3(n)
  r=110;
  p=75;
  a=0;
  b=2*pi;
  f=@(x)(60*r)/(r.^2-p.^2)*((1-((p/r).^2)*sin(x)).^(1/2));
  h=(b-a)/n;
  x=[];
  for i= 1:n-1
    x=[x a+i*h];
  endfor
  approx=((b-a)/(2*n)).*(f(a)+f(b)+2.*sum(f(x)))
endfunction