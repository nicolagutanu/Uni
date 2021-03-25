;math model:
;depth_aux(n,level,l1l2l3)= nil,l=[]
;                             level,l1=n
;                             depth_aux(n,level+1,l3),depth_aux(n,level+1,l2)=0
;                             depth_aux(n,level+1,l2),otherwise
;depth_aux(n:node we're looking for,level:resulting level,l:tree)   
(defun depth_aux8 (n level l)
    (cond
        ((null l) nil)
        ((equal (car l) n) level)
        ((null (depth_aux8 n (+ 1 level) (cadr l))) (depth_aux8 n (+ 1 level) (caddr l)))
        ((depth_aux8 n (+ 1 level) (cadr l)))
    )
)

;wrapper function:
;depth(n:node we're looking for,l:tree)
(defun depth8 (n l)
    (depth_aux8 n 1 l)
)

;math model:
;evenReplace(l1l2..ln)= nil,l=[]
;                       evenReplace(l1) U evenReplace(l2l3..ln), l1=list
;                       l1+1 U evenReplace(l2l3..ln), l1=even numeric atom
;                       l1 U evenReplace(l2l3..ln),otherwise
;evenReplace(l:list)
(defun evenReplace8(l k r)
	(cond
		((null l)nil)
		((listp (car l))(cons (evenReplace8 (car l) k r)(evenReplace8 (cdr l) k r)))
		((and (atom (car l)) (equal (mod (depth8 (car l) r) k) 0)) (cons 0 (evenReplace8 (cdr l) k r)))
		(t (cons (car l)(evenReplace8 (cdr l) k r)))
)
)

;wrapper
;evenReplaceWrapper(l:list,k:level to replace from)
(defun evenReplaceWrapper(l k)
	(evenReplace8 l k l)
)