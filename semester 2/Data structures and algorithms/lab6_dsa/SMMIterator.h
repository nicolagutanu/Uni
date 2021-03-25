#pragma once

#include "SortedMultiMap.h"
#include <stack>

using namespace std;


class SMMIterator{
	friend class SortedMultiMap;
private:
	//DO NOT CHANGE THIS PART
	SortedMultiMap& map;
	SMMIterator(SortedMultiMap& map);

	//TODO - Representation
	std::stack<int> traversal;
	int currentNode;

public:
	void first();
	void next();
	bool valid() const;
   	TElem getCurrent() const;
	//removes and returns the current pair from the iterator
	//after the operation the current pair from the Iterator is the next element from the MultiMap, or, if the removed element was the last one, the iterator is invalid
	//throws exception if the iterator is invalid
	TElem remove();

};

