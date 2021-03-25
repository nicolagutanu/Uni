#include "SMMIterator.h"
#include "SortedMultiMap.h"

SMMIterator::SMMIterator(SortedMultiMap& d) : map(d)
{
	//TODO - Implementation
	//inorder iterator
	//Theta(n), where n is the lenght of the binary tree
	int node = map.root;
	if (map.root != -1)
	{
		while (!this->traversal.empty())
			this->traversal.pop();
		while (node != -11 && node != -1)
		{
			this->traversal.push(node);
			node = map.left[node];
		}
		if (this->traversal.empty())
			this->currentNode = -11;
		else
			this->currentNode = this->traversal.top();
	}
	else
	{
		this->currentNode = -11;
	}
}

void SMMIterator::first()
{
	//TODO - Implementation
	//Theta(n), where n is the lenght of the binary tree
	int node = map.root;
	if (map.root != -1)
	{
		while (!this->traversal.empty())
			this->traversal.pop();
		while (node != -11 && node != -1)
		{
			this->traversal.push(node);
			node = map.left[node];
		}
		if (this->traversal.empty())
			this->currentNode = -11;
		else
			this->currentNode = this->traversal.top();
	}
	else
	{
		this->currentNode = -11;
	}
}

void SMMIterator::next()
{
	//TODO - Implementation
	//O(n), where n is the height
	if (this->traversal.empty())
	{
		throw exception();
	}
	else
	{
		int node = this->traversal.top();
		this->traversal.pop();

		if (map.right[node] != -11)
		{
			node = map.right[node];
			while (node != -11 && node != -1)
			{
				this->traversal.push(node);
				node = map.left[node];
			}
		}

		if (this->traversal.empty())
			this->currentNode = -11;
		else
			this->currentNode = this->traversal.top();
	}
}

bool SMMIterator::valid() const
{
	//TODO - Implementation
	//Theta(1)
	return this->currentNode != -11;
}

TElem SMMIterator::getCurrent() const
{
	//TODO - Implementation
	//Theta(1)
	if (this->traversal.empty())
		throw exception();
	else
		return map.info[this->currentNode];
}

TElem SMMIterator::remove()
{
	//Theta(1)
	TElem elem = this->getCurrent();
	this->map.remove(elem.first, elem.second);
	if (!this->valid())
		throw exception();
	else
	{
		this->next();
	}
	return elem;
}
