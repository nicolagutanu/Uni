function r=pb1
  #a
  a=0;
  b=1;
  fa=2./(1+a.^2);
  fb=2./(1+b.^2);
  approx_trapezium=((b-a)/2)*(fa+fb)
  
  #b
  hold on;
  x=[0:0.1:1];
  f=2./(1+x.^2);
  plot(x,f);
  plot([0,0,1,1],[0,fa,fb,0]);
  hold off;
  
  #c
  h=(a+b)/2;
  fh=2./(1+h.^2);
  approx_simpsons=((b-a)/6)*(fa+4*fh+fb)
endfunction