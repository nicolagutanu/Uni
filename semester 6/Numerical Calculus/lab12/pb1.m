function pb1
  f=@(x)x-cos(x);
  fd=@(x)1+sin(x);
  x0=pi/4;
  eps=10.^(-4);
  for n= 0:100
    x1=x0-(f(x0)/fd(x0));
    if abs(x1-x0)<eps
      x1
      return;
    endif
    x0=x1;
  endfor
endfunction