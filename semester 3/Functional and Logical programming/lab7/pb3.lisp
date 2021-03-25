;3. Define a function to tests the membership of a 
;node in a n-tree represented as 
;(rootlist_of_nodes_subtree1 ... list_of_nodes_subtreen)
;Eg. tree is (a (b (c)) (d) (E (f))) 
;and the node is "b" => true
;math model:
;cont(elem, l1l2...ln)=0, l=atom
;                      1+cont(elem,l2l3...ln), l1=elem
;                      cont(elem,l2l3...ln), otherwise

(defun cont(e L)
(cond
((equal L e) 1)
((atom L) 0)
(t (apply #'+ (mapcar #'(lambda(L)(cont e L))l)))
)
)

(defun wrapper(e l)
(if (> (cont e l) 0) (princ "true")
(wrapper e l)
))
				 
				 
				 
				 