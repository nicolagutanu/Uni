function r=pb3()
  x=[-5:0.3:5];
  f=sin(2.*x);
  fp=2.*cos(2.*x);
  hold on;
  plot(x,f);
  nodes=linspace(-5,5,15);
  r=[];
  for i= 1:length(nodes)
    r=[r hermite(x,f,fp,nodes(i))];
  endfor
  fprintf('%11.8f \n\n',r)
  plot(nodes,r);
  hold off;
endfunction

function r=hermite(x,f,fp,v)
  z=[];
  for i= 1:length(x)
    z=[z x(i) x(i)];
  endfor
  dist=f;
  f=[];
  for i= 1:length(dist)
    f=[f dist(i) dist(i)];
  endfor
  speed=fp;
  fp=[];
  for i= 1:length(speed)
    fp=[fp speed(i) speed(i)];
  endfor
  d=divdiff(z,f,fp);
  r=f(1);
  multy=1;
  for i= 1:length(z)-1
    multy=multy.*(v-z(i));
    r=r+round(multy.*d(i)*100)/100;
  endfor
endfunction

function r=divdiff(z,f,fp)
  format short g;
  n = length(z);
  mat = [f', NaN * zeros(n,n-1)];
  for i= 1:length(f)-1
    if mod(i,2)==0
      mat(i,2)=(mat(i+1,1)-mat(i,1))./(z(i+(1))-z(i));
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