function pb1(n)
  a=1;
  b=1.5;
  mid=(a+b)/2;
  f=@(x)exp(-x.^2);
  approx_a=(b-a)*f(mid)
  
  hold on;
  x=[a:0.01:b];
  plot(x,f(x));
  plot([a,b,b,a, a],[f(mid),f(mid),0,0,f(mid)],'r');
  hold off;
  
  x1=a+(b-a)/(2*n);
  approx_c=f(x1);
  for i= 2:n
    xi=x1+(i-1)*((b-a)/n);
    approx_c=approx_c+f(xi);
  endfor
  approx_c=approx_c*((b-a)/n)
endfunction