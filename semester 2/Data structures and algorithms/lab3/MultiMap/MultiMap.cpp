#include "MultiMap.h"
#include "MultiMapIterator.h"
#include <exception>
#include <iostream>

using namespace std;


MultiMap::MultiMap() 
{
	// Theta(capacity - 1)
	//TODO - Implementation
	this->capacity = 10;
	this->head = -1;
	this->tail = -1;
	this->length = 0;
	this->firstEmpty = 0;
	this->nodes = new Node[this->capacity];
	for (int i = 0; i < this->capacity - 1; i++)
	{
		this->nodes[i].next = i + 1;
		this->nodes[i].prev = i - 1;
	}
	this->nodes[this->capacity - 1].next = -1;
}

void MultiMap::resize()
{
	//Theta(length)
	this->firstEmpty = this->capacity;
	Node* aux = new Node[this->capacity * 2];
	int i = head;
	while (i != -1)
	{
		aux[i] = this->nodes[i];
		i = this->nodes[i].next;
	}
	delete[] this->nodes;
	this->nodes = aux;
	aux = nullptr;
	for (int i = this->capacity; i < this->capacity * 2; i++)
	{
		this->nodes[i].next = i + 1;
		if (i == this->capacity)
			this->nodes[i].prev = -1;
		else
			this->nodes[i].prev = i - 1;
		if (i == this->capacity * 2 - 1)
			this->nodes[i].next = -1;
	}
	this->capacity *= 2;
}

void MultiMap::print()
{
	// Theta(length)
	int i = this->head;
	while (i != -1)
	{
		cout << this->nodes[i].data.first << " " << this->nodes[i].data.second << "/";
		i = this->nodes[i].next;
	}
}


void MultiMap::add(TKey c, TValue v) 
{
	// Theta(1)
	//TODO - Implementation
	if (this->capacity - 1 == this->length)
		this->resize();
	Node nodeToAdd;
	nodeToAdd.data.first = c;
	nodeToAdd.data.second = v;
	int next_firstEmpty = this->nodes[this->firstEmpty].next;
	if (this->length == 0)
	{
		nodeToAdd.prev = -1;
		nodeToAdd.next = -1;
		this->head = this->firstEmpty;
		this->tail = this->firstEmpty;
		this->nodes[this->firstEmpty] = nodeToAdd;
		this->length += 1;
	}
	else
	{
		this->nodes[this->tail].next = this->firstEmpty;
		nodeToAdd.prev = this->tail;
		nodeToAdd.next = -1;
		this->tail = this->firstEmpty;
		this->nodes[this->firstEmpty] = nodeToAdd;
		this->length += 1;
	}
	this->firstEmpty = next_firstEmpty;
	if (this->firstEmpty != -1)
		this->nodes[this->firstEmpty].prev = -1;
}


bool MultiMap::remove(TKey c, TValue v) 
{
	// O(length)
	//TODO - Implementation
	if (search(c).size() == 0)
		return false;
	//the element to be removed is the only one in the list
	if (this->head == this->tail && this->nodes[this->head].data.first == c && this->nodes[this->head].data.second == v)
	{
		int indexRemoved = this->head;
		this->nodes[this->head].next = this->firstEmpty;
		this->firstEmpty = this->head;
		if (this->firstEmpty != -1)
			this->nodes[this->firstEmpty].prev = indexRemoved;
		this->head = -1;
		this->tail = -1;
		this->length -= 1;
		return true;
	}
	//the element to be removed is the tail
	if (this->nodes[this->tail].data.first == c && this->nodes[this->tail].data.second == v)
	{
		int indexRemoved = this->tail;
		this->nodes[this->nodes[this->tail].prev].next = this->nodes[this->tail].next;
		this->tail = this->nodes[this->tail].prev;
		this->length -= 1;
		this->nodes[indexRemoved].next = this->firstEmpty;
		this->firstEmpty = indexRemoved;
		if (this->firstEmpty != -1)
			this->nodes[this->firstEmpty].prev = indexRemoved;
		return true;
	}
	//the element to be removed is the head
	if (this->nodes[this->head].data.first == c && this->nodes[this->head].data.second == v)
	{
		int indexRemoved = this->head;
		this->nodes[this->nodes[this->head].next].prev = -1;
		this->head = this->nodes[this->head].next;
		this->length -= 1;
		this->nodes[indexRemoved].next = this->firstEmpty;
		this->firstEmpty = indexRemoved;
		if (this->firstEmpty != -1)
			this->nodes[this->firstEmpty].prev = indexRemoved;
		return true;
	}
	//the element to be removed could be anywhere in the list
	int i = this->head;
	while (i != -1)
	{
		if (this->nodes[i].data.first == c && this->nodes[i].data.second == v)
		{
			int indexRemoved = i;
			this->nodes[this->nodes[i].prev].next = this->nodes[i].next;
			this->nodes[this->nodes[i].next].prev = this->nodes[i].prev;
			this->length -= 1;
			this->nodes[indexRemoved].next = this->firstEmpty;
			this->firstEmpty = indexRemoved;
			if (this->firstEmpty != -1)
				this->nodes[this->firstEmpty].prev = indexRemoved;
			return true;
		}
		i = this->nodes[i].next;
	}
	return false;
}


vector<TValue> MultiMap::search(TKey c) const 
{
	// O(length)
	//TODO - Implementation
	vector<TValue> valuesOfKey;
	int j = this->head;
	while (j != -1)
	{
		if (this->nodes[j].data.first == c)
			valuesOfKey.push_back(this->nodes[j].data.second);
		j = this->nodes[j].next;
	}
	return valuesOfKey;
}


int MultiMap::size() const 
{
	// Theta(1)
	//TODO - Implementation
	return this->length;
}


bool MultiMap::isEmpty() const 
{
	// Theta(1)
	//TODO - Implementation
	if (this->length == 0)
		return true;
	return false;
}

MultiMapIterator MultiMap::iterator() const 
{
	// Theta(1)
	return MultiMapIterator(*this);
}


MultiMap::~MultiMap() 
{
	// Theta(1)
	//TODO - Implementation
	delete[] this->nodes;
}

