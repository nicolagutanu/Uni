function r=pb1
  x=[0,pi/2,pi,(3*pi)/2,2*pi];
  f=sin(x);
  v=pi/4;
  vf=sin(v)
  natural=spline(x,f,v)
  clamped=spline(x,[1 f 1],v)
  
  xx=[0:0.1:2*pi];
  fx=sin(xx);
  hold on;
  plot(xx,fx);
  naturalx=spline(x,f,xx);
  plot(xx,naturalx);
  clampedx=spline(x,[1 f 1],xx);
  plot(xx,clampedx);
  hold off;
endfunction