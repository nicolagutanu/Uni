function r=pb3
  time=[0,3,5,8,13];
  dist=[0,225,383,623,993];
  s=[75,77,80,74,72];
  position=spline(time,[75 dist 72],10)
  
  speed=spline(time,[0 s 0],10)
endfunction