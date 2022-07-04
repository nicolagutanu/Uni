function r=pb2_error(v)
  x=[1,2];
  r=[1,1];
  u=1;
  n=0;
  for i= 1:length(x)
    u=((v-x(i)).^r(i)+1).*((v-x(i)).^r(i)+1);
    n=n+r(i);
  endfor
  n=n+(length(x)-1);
  fact=factorial(n+1);
  y=[1:0.1:2];
  f=2.*y.^(-3);
  fv=max(f);
  r=(u./n)*fv;
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