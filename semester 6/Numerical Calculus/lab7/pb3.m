function r=pb3
  axis([0,3,0,5]);
  [x,y]=ginput(10);
  ls2=polyfit(x,y,2)
  hold on;
  plot(x,y,'x')
  v=[0:0.1:3];
  plot(v,polyval(ls2,v))
  hold off;
endfunction