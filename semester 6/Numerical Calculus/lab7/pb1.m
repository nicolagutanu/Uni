function r=pb1
  x=[1,2,3,4,5,6,7];
  f=[13,15,20,14,15,13,10];
  a=0;
  b=0;
  m=length(x);
  p1=0;
  p2=0;
  p3=0;
  p4=0;
  for i= 1:m
    p1=p1+x(i)*f(i);
    p2=p2+x(i);
    p3=p3+f(i);
    p4=p4+x(i).^2;
  endfor
  a=(m*p1-p2*p3)./(m*p4-p2.^2)
  b=(p4*p3-p1*p2)./(m*p4-p2.^2)
  result=a*8+b
  approx=sum((f-a*x-b).^2)
  hold on;
  plot(x,f,'xr');
  r=[];
  for i= 1:m
    r=[r a*x(i)+b];
  endfor
  plot(x,r);
  hold off;
endfunction