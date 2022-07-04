function pb3(n,x)
  m = length(x);
  y = ones(1,m);
  plot(x, y);
  for i = 1 : n
    y = y + (x.^i) / factorial(i);
    plot(x, y);
  endfor
end
