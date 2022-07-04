function r = pb3(x,f,v)
  r=[];
  for i= 1:length(v)
    r = [r pb(x,f,v(i))];
  endfor
endfunction

function L=pb(x,f,v)
  n=length(x);
  A=zeros(1,n);
  L=0;
  for i = 1:n
    A(i)=1./prod(x(i)-x([1:i-1, i+1:n]));
    s1=sum((A.*f)./(v-x));
    s2=sum(A./(v-x));
    L = sum(s1./s2);
  endfor
endfunction