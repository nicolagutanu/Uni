;math model:
;evenReplace(l1l2..ln)= nil,l=[]
;                       evenReplace(l1) U evenReplace(l2l3..ln), l1=list
;                       l1+1 U evenReplace(l2l3..ln), l1=even numeric atom
;                       l1 U evenReplace(l2l3..ln),otherwise
;evenReplace(l:list)
(defun evenReplace(l)
	(cond
		((null l)nil)
		((listp (car l))(cons(evenReplace (car l))(evenReplace (cdr l))))
		((and (numberp (car l)) (equal (mod (car l) 2) 0)) (cons (+ 1 (car l)) (evenReplace (cdr l))))
		(t (cons (car l)(evenReplace (cdr l))))
)
)

