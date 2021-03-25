#include <exception>
#include "BagIterator.h"
#include "Bag.h"
#include <iostream>

using namespace std;


BagIterator::BagIterator(const Bag& c) : bag(c)
{
	//TODO - Implementation
	/* time complexity: Theta(1) */
	current = 0;
}

void BagIterator::first() {
	//TODO - Implementation
	/* time complexity: Theta(1) */
	current = 0;
}


void BagIterator::next() {
	//TODO - Implementation
	/* time complexity: Theta(1) */
	if (!valid())
	{
		throw std::runtime_error{ "Invalid iterator" };
	}
	current += 1;
}


bool BagIterator::valid() const {
	//TODO - Implementation
	/* time complexity: Theta(1) */
	return current < bag.size_of_p;
}



TElem BagIterator::getCurrent() const
{
	//TODO - Implementation
	/* time complexity: Theta(1) */
	if (!valid())
	{
		throw std::runtime_error{ "Invalid iterator" };
	}
	return bag.u[bag.p[current]];
}