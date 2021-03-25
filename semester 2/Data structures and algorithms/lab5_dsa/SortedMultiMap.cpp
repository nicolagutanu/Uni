#include "SMMIterator.h"
#include "SortedMultiMap.h"
#include <iostream>
#include <vector>
#include <exception>
#include <cmath>
using namespace std;

SortedMultiMap::SortedMultiMap(Relation r)
{
	//Theta(13)
	//TODO - Implementation
	this->relation = r;
	this->capacity = 13;
	this->length = 0;
	this->firstEmpty = 0;
	this->nodes = new Node[this->capacity];
	this->next = new int[this->capacity];
	for (int i = 0; i < this->capacity; i++)
	{
		this->next[i] = -1;
		this->nodes->values = nullptr;
		this->nodes[i].length_values = 0;
		this->nodes[i].capacity_values = 0;
	}
}

void SortedMultiMap::add(TKey c, TValue v) 
{
	//O(capacity^2)
	//TODO - Implementation
	int h = this->hash(c);
	if (this->nodes[h].length_values == 0)
	{
		//case where the pair is the only one of its hash kind
		this->nodes[h].key = c;
		this->nodes[h].length_values += 1;
		this->nodes[h].capacity_values += 1;
		this->nodes[h].values = new TValue[this->nodes[h].capacity_values];
		this->nodes[h].values[0] = v;
	}
	else
	{
		for (this->firstEmpty = 0; this->firstEmpty < this->capacity; this->firstEmpty++)
			if (this->nodes[this->firstEmpty].length_values == 0)
			{
				int preh = h;
				while (h != -1 && this->nodes[h].key != c)
				{
					preh = h;
					h = this->next[h];
				}
				//case where c doesn't already exist
				if (h == -1)
				{
					h = this->firstEmpty;
					this->nodes[h].key = c;
					this->nodes[h].capacity_values = 10;
					this->nodes[h].values = new TValue[this->nodes[h].capacity_values];
					this->nodes[h].length_values += 1;
					this->nodes[h].values[0] = v;
					this->next[preh] = h;
					break;
				}
				else
				{
					//case where c already exists and may need resizing
					if (this->nodes[h].length_values == this->nodes[h].capacity_values)
					{
						TValue* newValues = new TValue[this->nodes[h].capacity_values * 2];
						for (int i = 0; i < this->nodes[h].capacity_values; i++)
							newValues[i] = this->nodes[h].values[i];
						delete[] this->nodes[h].values;
						this->nodes[h].values = newValues;
						this->nodes[h].capacity_values *= 2;
					}
					this->nodes[h].values[this->nodes[h].length_values] = v;
					this->nodes[h].length_values += 1;
					break;
				}
			}
	}
	if (this->firstEmpty == this->capacity)
		this->resize_rehash();
	this->length += 1;
}

bool SortedMultiMap::remove(TKey c, TValue v)
{
	//O(m*n), where m is the length of a values list and n is the capacity
	//TODO - Implementation
	int h = this->hash(c);
	if (this->search(c).size() == 0)
		return false;
	int preh = -1;
	while (h != -1 && this->nodes[h].key != c)
	{
		preh = h;
		h = this->next[h];
	}
	if (h == -1)
		return false;
	else
	{
		//case where the value isn't the only one in the list of values
		if (this->nodes[h].length_values > 1)
		{
			for (int i = 0; i < this->nodes[h].length_values; i++)
			{
				if (this->nodes[h].values[i] == v)
				{
					for (int j = i; j < this->nodes[h].length_values - 1; j++)
						this->nodes[h].values[j] = this->nodes[h].values[j + 1];
					this->nodes[h].length_values -= 1;
					this->length -= 1;
					for (this->firstEmpty = 0; this->firstEmpty < this->capacity; this->firstEmpty++)
						if (this->nodes[this->firstEmpty].length_values == 0)
							return true;
					return false;
				}
			}
		}
		else
		{
			//case where there only one value in the list of values
			if (this->nodes[h].values[0] == v)
			{
				delete[] this->nodes[h].values;
				while (h != -1)
				{
					preh = h;
					h = this->next[h];
					if (h != -1)
						this->nodes[preh] = this->nodes[h];
				}
				this->nodes[preh].values = nullptr;
				this->nodes[preh].length_values = 0;
				this->nodes[preh].capacity_values = 0;
				this->length -= 1;
				for (this->firstEmpty = 0; this->firstEmpty < this->capacity; this->firstEmpty++)
					if (this->nodes[this->firstEmpty].length_values == 0)
						return true;
			}
			else
				return false;
		}
	}
	return false;
}

vector<TValue> SortedMultiMap::search(TKey c) const
{
	//O(m+n), where n si the capacity and m is the length of a list of values
	//TODO - Implementation
	vector<TValue> valuesOfKey;
	int h = this->hash(c);
	if (this->nodes[h].length_values != 0)
	{
		while (h != -1 && this->nodes[h].key != c)
			h = this->next[h];
		if (h == -1)
			return valuesOfKey;
		else
		{
			for (int i = 0; i < this->nodes[h].length_values; i++)
				valuesOfKey.push_back(this->nodes[h].values[i]);
		}
	}
	return valuesOfKey;
}

int SortedMultiMap::size() const 
{
	//Theta(capacity)
	//TODO - Implementation
	int total = 0;
	for (int i = 0; i < this->capacity; i++)
		if (this->nodes[i].key != -111111)
			total += this->nodes[i].length_values;
	return total;
}

bool SortedMultiMap::isEmpty() const 
{
	//Theta(1)
	//TODO - Implementation
	return this->length == 0;
}

SMMIterator SortedMultiMap::iterator() 
{
	return SMMIterator(*this);
}

SortedMultiMap::~SortedMultiMap() 
{
	//Theta(1)
	//TODO - Implementation
	/*for (int i = 0; i < this->capacity; i++)
		if (this->nodes[i].values != nullptr)
			delete[] this->nodes[i].values;
	delete[] this->nodes;
	delete[] this->next;*/
	delete[] nodes;
}

void SortedMultiMap::resize_rehash()
{
	//Theta(m) where m is the number of key-value pairs
	int oldCap = this->capacity;
	this->capacity = this->firstPrimeGreaterThan(this->capacity);
	Node* newNodes = new Node[this->capacity];
	int* newNext = new int[this->capacity];
	vector<TElem> allPairs;
	for (int i = 0; i < oldCap; i++)
	{
		for (int j = 0; j < this->nodes[i].length_values; j++)
		{
			TElem e;
			e.first = this->nodes[i].key;
			e.second = this->nodes[i].values[j];
			allPairs.push_back(e);
		}
	}
	for (int i = 0; i < this->capacity; i++)
	{
		newNext[i] = -1;
		newNodes[i].values = nullptr;
		newNodes[i].length_values = 0;
		newNodes[i].capacity_values = 0;
	}
	delete[] this->nodes;
	delete[] this->next;
	this->nodes = newNodes;
	this->next = newNext;
	this->length = 0;
	for (int i = 0; i < allPairs.size(); i++)
	{
		TElem e = allPairs[i];
		this->add(e.first, e.second);
	}
}

int SortedMultiMap::hash(TKey k) const
{
	//Theta(1)
	int aux = abs(int(k));
	return aux % this->capacity;
}

bool SortedMultiMap::prime(int x)
{
	//Theta(x)
	if (x < 2)
		return false;
	for (int d = 2; d * d < x; d++)
		if (x % d == 0)
			return false;
}

int SortedMultiMap::firstPrimeGreaterThan(int x)
{
	//Theta(x)
	x += 1;
	while (prime(x) == false)
		x += 1;
	return x;
}