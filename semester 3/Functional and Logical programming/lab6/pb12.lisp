;12. Determine the list of nodes accesed in preorder 
;in a tree of type (2).
;math model:
;preodr(l1l2l3)=[],n=0
;               l1 U preodr(l2) U preodr(l3), otherwise
;where l2 and l3 are sublists

(defun preodr(l)
(if(null l)nil
(cons(car l)(append(preodr(car(cdr l)))(preodr(car(cdr(cdr l))))))
)
)