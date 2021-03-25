#include "Bag.h"
#include "BagIterator.h"
#include <exception>
#include <iostream>
using namespace std;


Bag::Bag() {
	//TODO - Implementation
	capacity = 10;
	size_of_u = 0;
	size_of_p = 0;
	u = new TElem[capacity];
	p = new TElem[capacity];
}


void Bag::add(TElem elem) {
	//TODO - Implementation
	/* time complexity O(n), where n is the number of unique elements */
	int pos = -1;
	for (int i = 0; i < size_of_u; i++)
	{
		if (u[i] == elem)
			pos = i;
	}
	if (capacity == size_of_p)
	{
		capacity *= 2;
		TElem* old_data_u, * old_data_p;
		TElem* new_data_u, * new_data_p;
		old_data_u = u;
		old_data_p = p;
		new_data_u = new TElem[capacity];
		new_data_p = new TElem[capacity];
		for (int i = 0; i < size_of_u; i++)
		{
			new_data_u[i] = old_data_u[i];
		}
		for (int i = 0; i < size_of_p; i++)
		{
			new_data_p[i] = old_data_p[i];
		}
		delete[] u;
		delete[] p;
		u = new_data_u;
		p = new_data_p;

	}
	if (pos == -1)
	{
		u[size_of_u] = elem;
		p[size_of_p] = size_of_u;
		size_of_u += 1;
		size_of_p += 1;
	}
	else
	{
		p[size_of_p] = pos;
		size_of_p += 1;
	}
}


bool Bag::remove(TElem elem) {
	//TODO - Implementation
	/* time complexity O(n) */
	int pos = -1;
	for (int i = 0; i <= size_of_u; i++)
	{
		if (u[i] == elem)
			pos = i;
	}
	if (pos == -1)
		return false;
	else
	{
		if (nrOccurrences(u[pos]) == 1)
		{
			for (int i = pos; i < size_of_u; i++)
			{
				u[i] = u[i + 1];
			}
			size_of_u -= 1;
			int i = 0;
			while (pos != p[i])
				i += 1;
			for (int j = i; j < size_of_p; j++)
			{
				p[j] = p[j + 1];
			}
			size_of_p -= 1;
			for (int i = 0; i < size_of_p; i++)
				if (p[i] > pos)
					p[i] -= 1;
			return true;
		}
		else
		{
			int i = 0;
			while (pos != p[i])
				i += 1;
			for (int j = i; j < size_of_p; j++)
			{
				p[j] = p[j + 1];
			}
			size_of_p -= 1;
			return true;
		}
	}
}


bool Bag::search(TElem elem) const {
	//TODO - Implementation
	/* time complexity O(n), where n is the number of unique elements */
	for (int i = 0; i <= size_of_u; i++)
	{
		if (u[i] == elem)
			return true;
	}
	return false;
}

int Bag::nrOccurrences(TElem elem) const {
	//TODO - Implementation
	/* time complexity O(n) */
	int count = 0;
	int pos = -1;
	for (int i = 0; i < size_of_u; i++)
	{
		if (u[i] == elem)
			pos = i;
	}
	if (pos == -1)
		return 0;
	else
	{
		for (int i = 0; i < size_of_p; i++)
		{
			if (p[i] == pos)
				count += 1;
		}
		return count;
	}
}


int Bag::size() const {
	//TODO - Implementation
	/* time complexity Theta(1) */
	if (size_of_p == 0)
	{
		return 0;
	}
	else
		return size_of_p;
}


bool Bag::isEmpty() const {
	//TODO - Implementation
	/* time complexity Theta(1) */
	if (size_of_u == 0)
		return true;
	else
		return false;
}

BagIterator Bag::iterator() const {
	//TODO - Implementation
	/* time complexity Theta(1) */
	return BagIterator(*this);
}


Bag::~Bag() {
	//TODO - Implementation
	/* time complexity Theta(1) */
	if (u != NULL)
		delete[] u;
	if (p != NULL)
		delete[] p;
}

int Bag::removeAllOccurrences(TElem elem)
{
	/* best worst = Theta(n*n) best case = Theta(n) overall = O(n*n)*/
	int nrOccur = nrOccurrences(elem);
	int pos = -1;
	for (int i = 0; i <= size_of_u; i++)
	{
		if (u[i] == elem)
			pos = i;
	}
	if (pos == -1)
		return 0;
	else
	{
		for (int i = pos; i < size_of_u; i++)
		{
			u[i] = u[i + 1];
		}
		size_of_u -= 1;
		int occur = nrOccur;
		while (occur > 0)
		{
			int i = 0;
			while (pos != p[i])
				i += 1;
			for (int j = i; j < size_of_p; j++)
			{
				p[j] = p[j + 1];
			}
			size_of_p -= 1;
			occur -= 1;
		}
		for (int i = 0; i < size_of_p; i++)
			if (p[i] > pos)
				p[i] -= 1;
		return nrOccur;
	}
}