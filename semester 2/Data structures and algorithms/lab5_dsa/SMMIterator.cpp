#include "SMMIterator.h"
#include "SortedMultiMap.h"
#include <exception>
#include <iostream>

SMMIterator::SMMIterator(SortedMultiMap& d) : map(d)
{
	//Theta(m*n), where n is the capacity and m is the length of each value list
	//TODO - Implementation
	this->currentNode = 0;
	this->length_it = map.size();
	this->elems = new TElem[this->length_it];
	this->r = map.relation;
	int l = 0;
	/*for (int i = 0; i < map.capacity; i++)
		if (map.nodes[i].length_values != 0)
		{ 
			this->elems[l] = map.nodes[i];
			l += 1;
		}*/
	for (int i=0; i<map.capacity; i++)
		if (map.nodes[i].length_values!=0)
			for (int j = 0; j < map.nodes[i].length_values; j++)
			{
				this->elems[l] = TElem{ map.nodes[i].key, map.nodes[i].values[j] };
				l += 1;
			}
	for (int i = 0; i < l - 1; i++)
		for (int j = i + 1; j < l; j++)
			if (!this->r(this->elems[i].first, this->elems[j].first))
				swap(this->elems[i], this->elems[j]);
	/*for (int i = 0; i < l; i++)
		cout << this->elems[i].first << "-" << this->elems[i].second << endl;*/
}

void SMMIterator::first()
{
	//Theta(1)
	//TODO - Implementation
	this->currentNode = 0;
}

void SMMIterator::next()
{
	//Theta(1)
	//TODO - Implementation
	if (!this->valid())
		throw exception();
	this->currentNode+=1;
}

bool SMMIterator::valid() const
{
	//Theta(1)
	//TODO - Implementation
	return this->currentNode < this->length_it;
}

TElem SMMIterator::getCurrent() const
{
	//Theta(1)
	//TODO - Implementation
	if (!this->valid())
		throw exception();

	return this->elems[this->currentNode];
	/*int l = 0;
	TElem e;
	for (int i = 0; i < this->length_it; i++)
	{
		for (int j = 0; j < this->elems[i].length_values; j++)
		{
			if (l == this->currentNode)
			{
				e.first = this->elems[i].key;
				e.second = this->elems[i].values[j];
				l += 1;
			}
			else
			{
				l += 1;
			}
		}
	}
	return e;*/
}

TElem SMMIterator::remove()
{
	//Theta(1);
	TElem elem = this->getCurrent();
	this->map.remove(elem.first, elem.second);
	if (!this->valid())
		throw exception();
	else
	{
		this->currentNode += 1;
	}
	return elem;
}


