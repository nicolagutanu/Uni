#include "MultiMapIterator.h"
#include "MultiMap.h"
#include <exception>
#include <iostream>


MultiMapIterator::MultiMapIterator(const MultiMap& c): col(c) 
{
	// Theta(1)
	this->first();
}

TElem MultiMapIterator::getCurrent() const
{
	// Theta(1)
	if (!valid())
	{
		throw std::runtime_error{ "Invalid iterator" };
	}
	TElem current_element;
	current_element.first = this->keys->key;
	current_element.second = this->nodes->value;
	return current_element;
}

bool MultiMapIterator::valid() const 
{
	// Theta(1)
	if (this->keys != NULL)
		return true;
	else
		return false;
}

void MultiMapIterator::next() 
{
	// Theta(1)
	if (!valid())
	{
		throw std::runtime_error{ "Invalid iterator" };
	}
	this->nodes = this->nodes->next;
	if (this->nodes == NULL)
	{
		this->keys = this->keys->nextKey;
		if (this->keys != NULL)
			this->nodes = this->keys->headValue;
		else
			this->nodes = NULL;
	}
}

void MultiMapIterator::first() 
{
	// Theta(1)
	this->keys = this->col.elems;
	if (this->keys != NULL)
		this->nodes = this->col.elems->headValue;
	else
		this->nodes = NULL;
}

