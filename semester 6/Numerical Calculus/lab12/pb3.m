function pb3
  x0=1;
  x1=2;
  f=@(x)x.^3-x.^2-1;
  eps=10.^(-4);
  for n= 0:100
    x2=x1-f(x1)*((x1-x0)/(f(x1)-f(x0)));
    if abs(x2-x1)<eps
      x2
      return;
    endif
    x0=x1;
    x1=x2;
  endfor
endfunction