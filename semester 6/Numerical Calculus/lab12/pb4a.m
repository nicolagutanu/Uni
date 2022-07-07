function pb4a
  a=1;
  b=2;
  f=@(x)(x-2).^2-log(x);
  if f(a)*f(b)>0
    error=1
    return;
  endif
  eps=10.^(-4);
  for n= 0:100
    c=(a+b)/2;
    if f(a)*f(c)<=0
      b=c;
    else
      a=c;
    endif
    if abs(f(c))<eps
      c
      return;
    endif
  endfor
endfunction