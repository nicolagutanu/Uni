function pb2a
  a=0;
  b=1;
  f=@(x)2/(1+x.^2);
  h=b-a;
  eps=10.^(-4);
  q0=(h/2)*(f(a)+f(b));
  found=0;
  k=1;
  while found==0
    qk=(1/2)*q0;
    s=0;
    for i= 1:2.^(k-1)
      s=s+f(a+((2*i-1)/2.^k)*h);
    endfor
    s=s*(h/2.^k);
    qk=qk+s;
    if abs(qk-q0)<=eps
      found=1;
    endif
    q0=qk;
    k=k+1;
  endwhile
  qk
endfunction