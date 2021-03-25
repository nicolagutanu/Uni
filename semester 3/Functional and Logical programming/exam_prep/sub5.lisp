;math model:
;del(l1l2..ln,e)= nil,l=[]
;                 del(l2l3..ln,e), l1=e
;				  del(l1,e) U del(l2l3..ln,e),l1=list
;                 l1 U del(l2l3..ln,e),otherwise
;del(l:list,e:element to be deleted)
(defun del5(l e)
	(cond
		((null l)nil)
		((and (atom (car l)) (equal (car l)e))(del5 (cdr l) e))
		((listp(car l))(cons (del5 (car l)e)(del5 (cdr l)e)))
		(t (cons (car l) (del5 (cdr l)e)))
)
)