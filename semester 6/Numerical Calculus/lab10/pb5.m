function pb5
  for n= 10:15
    h=ones(n);
    for i=1:n
      for j=1:n
        h(i,j)=1/(i+j-1);
      endfor
    endfor
    cond(h)
  endfor
endfunction