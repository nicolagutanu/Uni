function r=pb2
  t=[0 10 20 30 40 60 80 100];
  p=[0.0061 0.0123 0.0234 0.0424 0.0738 0.1992 0.4736 1.0133];
  ls4=polyfit(t,p,4);
  ls7=polyfit(t,p,7);
  r4=polyval(ls4,45)
  r7=polyval(ls7,45)
  e4=abs(0.095848-r4)
  e7=abs(0.095848-r7)
  
  hold on;
  plot(t,p);
  plot(45,r4,'xr');
  plot(45,r7,'ob');
  hold off;
endfunction