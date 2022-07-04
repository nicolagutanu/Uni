function pb1
  #a
  A=[10 7 8 7; 7 5 6 5; 8 6 10 9; 7 5 9 10];
  b=[32;23;33;31];
  xa=A\b
  a=cond(A)
  
  #b
  bb=[32.1;22.9;33.1;30.9];
  xb=A\bb
  in_rel_err=norm(b-bb)/norm(b)
  out_rel_err=norm(xa-xb)/norm(xa)
  div=out_rel_err/in_rel_err
  
  #c
   Ac=[10 7 8.1 7.2; 7.08 5.04 6 5; 8 5.98 9.89 9; 6.99 4.99 9 9.98];
   xc=Ac\b
   in_err=norm(A-Ac)/norm(A)
   out_err=norm(xa-xc)/norm(xa)
   div_c=out_err/in_err
endfunction