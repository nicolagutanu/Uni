function pb2
  f=@(e)e-0.8*sin(e)-((2*pi)/10);
  fd=@(x)1-0.8*cos(e);
  e0=1;
  for n= 0:6
    e1=e0-(f(e0)/fd(e0));
    eps=abs(e1-e0);
    e0=e1
  endfor
  eps
endfunction