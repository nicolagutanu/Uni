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
  r=mat;
endfunction