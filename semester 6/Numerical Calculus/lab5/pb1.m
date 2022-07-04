function r=pb1(v)
  time=[0,3,5,8,13];
  z=[];
  for i= 1:length(time)
    z=[z time(i) time(i)];
  endfor
  dist=[0,225,383,623,993];
  f=[];
  for i= 1:length(dist)
    f=[f dist(i) dist(i)];
  endfor
  speed=[75,77,80,74,72];
  fp=[];
  for i= 1:length(speed)
    fp=[fp speed(i) speed(i)];
  endfor
  d=divdiff(z,f,fp);
  r=f(1);
  multy=1;
  for i= 1:length(z)-1
    multy=multy.*(v-z(i));
    r=r+multy.*d(i);
  endfor
endfunction

function r=divdiff(z,f,fp)
  format short g;
  n = length(z);
  mat = [f', NaN * zeros(n,n-1)];
  for i= 1:length(f)-1
    if mod(i,2)==0
      mat(i,2)=(mat(i+1,1)-mat(i,1))./(z(i+(1))-z(i));;
    else
      mat(i,2)=fp(i);
    endif
  endfor
  for i = 3:n
    for j = 1:n-i+1
      mat(j,i)=(mat(j+1,i-1)-mat(j,i-1)) ./ (z(j+(i-1))-z(j));
    endfor
  endfor
  r=mat(1,2:end);
endfunction