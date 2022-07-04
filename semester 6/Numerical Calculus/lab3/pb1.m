function L=pb1(x,f,v)
  n=length(x);
  A=zeros(1,n);
  L=0;
  for i = 1:n
    A(i)=1./prod(x(i)-x([1:i-1, i+1:n]));
    s1=sum((A.*f)./(v-x));
    s2=sum(A./(v-x));
    L = sum(s1./s2);
  endfor
  L=round(L);
endfunction