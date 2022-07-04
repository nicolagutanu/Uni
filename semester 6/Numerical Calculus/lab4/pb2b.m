function r=pb2b()
  x=[1,2,3,4,5];
  f=[22,23,25,30,28];
  y=[1:0.1:5];
  r=[];
  for i= 1:length(y)
    r=[r newton(x,f,y(i))];
  endfor
endfunction

function r=newton(x,f,v)
  d=div_diff(x,f);
  r=f(1);
  multy=1;
  for i= 1:length(x)-1
    multy=multy.*(v-x(i));
    r=r+multy.*d(i);
  endfor
endfunction

function y=div_diff(x,f)
  n = length(x);
  mat = [f', NaN * zeros(n,n-1)];
  for i = 2:n
    for j = 1:n-i+1
      mat(j,i)=(mat(j+1,i-1)-mat(j,i-1)) ./ (x(j+(i-1))-x(j));
    endfor
  endfor
  y=mat(1,2:end);
endfunction