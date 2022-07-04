function pb2
  for n= 10:15
    v=[];
    for k= 1:n
      tk=1/k;
      row=[];
      for j= 1:n
        row=[row tk.^(j-1)];
      endfor
      v=[v;row];
    endfor
    cond(v)
  endfor
endfunction