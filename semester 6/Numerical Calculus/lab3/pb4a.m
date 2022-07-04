function r=pb4a(n)
  y=[-5:0.1:5];
  x=[];
  for i= 0:n
    x = [x (i*10)/n-5];
  endfor
  f=1./(1+x.^2);
  r=[];
  length(y)
  for i= 1:length(y)
    r = [r pb(x,f,y(i))];
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