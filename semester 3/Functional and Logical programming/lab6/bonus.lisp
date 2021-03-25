(defun prod(l)
(cond 
	((null l)1)
	((numberp (car l))(* (car l) (prod(cdr l))))
	((listp(car l))(* (prod(car l)) (prod(cdr l))))
	(t(prod(cdr l)))
))

(defun maxim(a b c)
(cond
	((and (> a b) (> a c)) a)
	((and (> b a) (> b c)) b)
	(t c)
))