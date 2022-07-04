function pb2b
  a=0;
  b=1;
  f=@(x)2/(1+x.^2);
  eps=10.^(-4);
  found=0;
  n=2;
  row=2;
  r1=[repeated_trapezium(1)];
  while found==0
    r2=[repeated_trapezium(n)];
    for i= 2:row
      val=((4.^(-i)*r1(i-1))-r2(length(r2)))/(4.^(-i)-1);
      r2=[r2 val];
    endfor
    if abs(r2(length(r2))-r1(length(r1)))<=eps
      found=1;
    endif
    r1=r2;
    n=n*2;
  endwhile
  r2(length(r2))
endfunction

function r=repeated_trapezium(n)
  a=0;
  b=1;
  f=@(x)2./(1+x.^2);
  h=(b-a)/n;
  x=[];
  for i= 1:n-1
    x=[x a+i*h];
  endfor
  r=((b-a)/(2*n)).*(f(a)+f(b)+2.*sum(f(x)));
endfunction