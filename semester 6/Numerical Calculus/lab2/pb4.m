function y=pb4(x,f)
  format short g;
  n = length(x);
  mat = [f', NaN * zeros(n,n-1)];
  for i = 2:n
    for j = 1:n-i+1
      mat(j,i)=mat(j+1,i-1)-mat(j,i-1);
    endfor
  endfor
  y=mat;
endfunction