;a: Write a function that inserts in a linear list 
;a given atom A after the 2nd, 4th, 6th, ... element.
;math model:
;addEl(a,l1l2..ln)= [],n=0
;                   l1 U a U addEl(a,l2l3..ln), n%2=0
;                   addEl(a,l1l2..ln)
;where a=given element to be added
;      len=growing length of list to check pos
(defun addEl(a len l)
(cond((null l)nil)
((equal (mod len 2) 0)(cons(car l)(cons a(addEl a (+ 1 len) (cdr l)))))
(t(cons(car l)(addEl a (+ 1 len) (cdr l))))
)
)

;b: Write a function to get from a given list the 
;list of all atoms, on anylevel, but reverse order.
;math model:
;help_list(h, l1l2..ln)= h, l=[]
;                        l1l2..ln-1 U ln, otherwise   
(defun help_list(h l)
(if (null l) h
(cons(car l)(help_list h (cdr l)))
)
)

;math model:
;atoms(l1l2..ln)= [], l=[]
;                 help_list(atoms(l1), atoms(l2l3..ln)), l1=list
;                 help_list(l1, atoms(l2l3..ln)), otherwise
(defun atoms(l)
(cond((null l)nil)
((listp(car l))(help_list (atoms(car l)) (atoms(cdr l))))
(t(help_list (list(car l)) (atoms(cdr l))))
)
)

;c: ) Write a function that returns the greatest 
;common divisor of all numbers in a nonlinear list.
;math model:
;gcd(a, b)= a, a=b
;           gcd(a-b, b), a>b
;           gcd(a, b-a), otherwise

(defun gcd2(a b)
(cond ((and (not(numberp a)) (not(numberp b)))nil)
((not(numberp a)) b)
((not(numberp b)) a)
((equal a b) a)
((> a b) (gcd2 (- a b) b))
(t(gcd2 a (- b a)))
)
)

;math model:
;gcd_list(l)= [], l=[]
;             gcd2(gcd_list(l1), gcd_list(l2l3..ln)), l1=list
;             gcd2(l1, gcd_list(l2l3..ln)), otherwise
(defun gcd_list(l)
(cond((null l)nil)
((listp(car l))(gcd2 (gcd_list(car l)) (gcd_list(cdr l))))
(t(gcd2 (car l) (gcd_list(cdr l))))
)
)

;d: ) Write a function that determines the number of
;occurrences of a given atom in a nonlinear list.
;math model:
;count(l1l2...ln,elem)= 0, l=[]
;                       1+count(l2l3...ln,elem), l1=elem
;                       count(l2l3...ln,elem), otherwise
(defun count2(elem l)
(cond((null l) 0)
((and (numberp(car l)) (equal (car l) elem)) (+ 1 (count2 elem (cdr l)))) 
(t(count2 elem (cdr l)))
)
)

;math model:
;count_list(elem, l1l2..ln)= 0, l=[]
;                            count_list(elem,l2l3..ln)+count_list(elem,l1), l1=list
;						     1+count_list(elem,l2l3..ln), l1=elem
;							 count_list(elem,l2l3..ln), otherwise
(defun count_list(elem l)
(cond((null l) 0)
((listp(car l))(+ (count_list elem (cdr l)) (count_list elem (car l))))
((and (numberp(car l)) (equal (car l) elem)) (+ 1 (count_list elem (cdr l))))
(t(count_list elem (cdr l)))
)
)














