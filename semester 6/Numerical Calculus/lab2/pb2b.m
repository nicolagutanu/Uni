function y = pb2b(n,x,i)
  m = length(x);
  if i == 0
      y = ones(1,m);
      plot(x,y);
  else
      if i == 1
          y = x;
          plot(x,y);
          pb2b(n,x,i-1);
      else
          y = 2 * x .* pb2b(n,x,i-1)-pb2b(n,x,i-2);
          plot(x, y);
          pb2b(n,x,i-1);
      end
  end
end