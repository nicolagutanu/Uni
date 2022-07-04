function r=pb2
  [x,y,b]=ginput(5);
  hold on;
  plot(x,y,'+b');
  natural=spline(x,y,x);
  plot(x,natural,'0r');
  hold off;
endfunction