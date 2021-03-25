#pragma once
#include "MultiMap.h"

class MultiMap;

class MultiMapIterator
{
	friend class MultiMap;

private:
	const MultiMap& col;
	//TODO - Representation
	int currentElement;
	MultiMapIterator(const MultiMap& c);

public:
	TElem getCurrent()const;
	bool valid() const;
	void next();
	void first();

	//moves the current element from the iterator k steps backward, or makes the iterator invalid 
	//if there are less than k elements left until the beginning of the MultiMap.
	//throws an exception if the iterator is invalid or if k is zero or negative
	void jumpBackward(int k);

};

