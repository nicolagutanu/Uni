#include "MultiMapIterator.h"
#include "MultiMap.h"


MultiMapIterator::MultiMapIterator(const MultiMap& c): col(c) 
{
	// Theta(1)
	//TODO - Implementation
	this->currentElement = this->col.head;
}

TElem MultiMapIterator::getCurrent() const
{
	// Theta(1)
	//TODO - Implementation
	if (!this->valid())
		throw exception();
	return this->col.nodes[this->currentElement].data;
}

bool MultiMapIterator::valid() const 
{
	// Theta(1)
	//TODO - Implementation
	if (this->col.size() == 0 || this->currentElement == -1)
		return false;
	return true;
}

void MultiMapIterator::next() 
{
	// Theta(1)
	//TODO - Implementation
	if (!this->valid())
		throw exception();
	this->currentElement = this->col.nodes[this->currentElement].next;
}

void MultiMapIterator::first() 
{
	// Theta(1)
	//TODO - Implementation
	this->currentElement = this->col.head;
}

void MultiMapIterator::jumpBackward(int k)
{
	//best case = Theta(how many elements until you get to end), worst case = Theta(k), average = O(k)
	if (!this->valid() || k < 1)
		throw exception();
	int count = this->currentElement;
	while (count != -1 && k != 0)
	{
		count = this->col.nodes[count].prev;
		k -= 1;
	}
	if (k == 0)
		this->currentElement = count;
	else
		this->currentElement = -1;

}

