#include "SMMIterator.h"
#include "SortedMultiMap.h"
#include <iostream>
#include <vector>
#include <exception>
using namespace std;

SortedMultiMap::SortedMultiMap(Relation r) 
{
	//TODO - Implementation
	//Theta(n), where n is the capacity
	this->relation = r;
	this->length = 0;
	this->capacity = 10;
	this->firstEmpty = 0;
	this->root = -1;

	this->info = new TElem[this->capacity];
	this->right = new int[this->capacity];
	this->left = new int[this->capacity];
	this->parent = new int[this->capacity];

	for (int i = 0; i < this->capacity; i++)
	{
		this->info[i] = NULL_TELEM;
		this->right[i] = -11;
		this->left[i] = i + 1;
		this->parent[i] = -11;
	}
	this->left[this->capacity - 1] = -1;
}

void SortedMultiMap::add(TKey c, TValue v) 
{
	//TODO - Implementation
	//Theta(n), where n is the number of nodes until the parent
	if (this->firstEmpty == -1)
		this->resize();

	//adding the root
	if (this->root == -1)
	{
		this->info[this->firstEmpty] = TElem{ c, v };
		this->parent[this->firstEmpty] = -1;
		this->root = this->firstEmpty;
		int oldEmpty = this->firstEmpty;
		this->firstEmpty = this->left[this->firstEmpty];
		this->left[oldEmpty] = -11;
		this->length += 1;
		return;
	}

	//finding the parent of the new node
	int currentNode = this->root;
	int parentNode = -1;
	while (currentNode != -11)
	{
		parentNode = currentNode;
		if (!this->relation(this->info[currentNode].first, c))
			currentNode = this->left[currentNode];
		else
			currentNode = this->right[currentNode];
	}

	//adding any other node in the tree
	this->info[this->firstEmpty] = TElem{ c,v };
	this->parent[this->firstEmpty] = parentNode;
	if (!this->relation(this->info[parentNode].first, c))
		this->left[parentNode] = this->firstEmpty;
	else
		this->right[parentNode] = this->firstEmpty;
	int oldEmpty = this->firstEmpty;
	this->firstEmpty = this->left[this->firstEmpty];
	this->left[oldEmpty] = -11;
	this->length += 1;
}

vector<TValue> SortedMultiMap::search(TKey c) const 
{
	//TODO - Implementation
	//Theta(n), where n is the length of the binary tree
	vector<TValue> valuesOfKey;
	if (this->root == -1)
		return valuesOfKey;
	
	int currentNode = this->root;
	while (currentNode != -11)
	{
		if (this->relation(this->info[currentNode].first, c))
		{
			if (this->info[currentNode].first == c)
				valuesOfKey.push_back(this->info[currentNode].second);
			currentNode = this->right[currentNode];
		}
		else
			currentNode = this->left[currentNode];
	}
	return valuesOfKey;
}

bool SortedMultiMap::remove(TKey c, TValue v) 
{
	//TODO - Implementation
	//O(n), where n is the length of the binary tree
	if (this->root == -1)
		return false;

	if (this->search(c).size() == 0)
		return false;

	int currentNode = this->root;
	int parentNode = -1;
	while (currentNode != -11)
	{
		if (this->info[currentNode] == TElem{ c,v })
		{
			//case where node is a leaf
			if (this->left[currentNode] == -11 && this->right[currentNode] == -11)
			{
				//case where it's the root
				if (currentNode == this->root)
				{
					this->root == -1;
					this->left[currentNode] = this->firstEmpty;
					this->firstEmpty = currentNode;
				}
				//case where it's a random node in the tree
				else
				{
					if (this->left[parentNode] == currentNode)
						this->left[parentNode] = -11;
					else
						this->right[parentNode] = -11;
					this->left[currentNode] = this->firstEmpty;
					this->firstEmpty = currentNode;
				}
				this->length -= 1;
				return true;
			}
			else if (this->right[currentNode] == -11 && this->left[currentNode] != -11)
			{//case where right node is empty and left one has a pair
				
				//case where node is the root
				if (this->root == currentNode)
				{
					this->root = this->left[currentNode];
					this->left[currentNode] = this->firstEmpty;
					this->right[currentNode] = -11;
					this->info[currentNode] = NULL_TELEM;
					this->firstEmpty = currentNode;
				}
				//case where it's a random node in the tree
				else
				{
					if (this->left[parentNode] == currentNode)
						this->left[parentNode] = this->left[currentNode];
					else
						this->right[parentNode] = this->left[currentNode];
					this->left[currentNode] = this->firstEmpty;
					this->right[currentNode] = -11;
					this->info[currentNode] = NULL_TELEM;
					this->firstEmpty = currentNode;
				}
				this->length -= 1;
				return true;
			}
			else if (this->right[currentNode] != -11 && this->left[currentNode] == -11)
			{//case where right node has a pair and left node is empty

				//case where node is the root
				if (this->root == currentNode)
				{
					this->root = this->right[currentNode];
					this->left[currentNode] = this->firstEmpty;
					this->right[currentNode] = -11;
					this->info[currentNode] = NULL_TELEM;
					this->firstEmpty = currentNode;
				}
				//case where it's a random node in the tree
				else
				{
					if (this->left[parentNode] == currentNode)
						this->left[parentNode] = this->right[currentNode];
					else
						this->right[parentNode] = this->right[currentNode];
					this->left[currentNode] = this->firstEmpty;
					this->right[currentNode] = -11;
					this->info[currentNode] = NULL_TELEM;
					this->firstEmpty = currentNode;
				}
				this->length -= 1;
				return true;
			}
			else
			{//case where left and right node have pairs
				int min = this->right[currentNode];
				while (this->left[min] != -11)
					min = this->left[min];
				this->remove(this->info[min].first, this->info[min].second);
				this->info[currentNode] = this->info[min];
			}
			return true;
		}
		parentNode = currentNode;
		if (!this->relation(this->info[currentNode].first, c))
			currentNode = this->left[currentNode];
		else
			currentNode = this->right[currentNode];
	}
	return false;
}


int SortedMultiMap::size() const 
{
	//TODO - Implementation
	//Theta(1)
	return this->length;
}

bool SortedMultiMap::isEmpty() const
{
	//TODO - Implementation
	//Theta(1)
	return this->length == 0;
}

SMMIterator SortedMultiMap::iterator()
{
	//Theta(1)
	return SMMIterator(*this);
}

SortedMultiMap::~SortedMultiMap() 
{
	//TODO - Implementation
	//Theta(1)
	delete[] this->info;
	delete[] this->left;
	delete[] this->right;
	delete[] this->parent;
}

void SortedMultiMap::resize()
{
	//Theta(n), where n is the capacity*2
	TElem* newInfo = new TElem[this->capacity * 2];
	int* newLeft = new int[this->capacity * 2];
	int* newRight = new int[this->capacity * 2];
	int* newParent = new int[this->capacity * 2];
	
	for (int i = 0; i < this->capacity; i++)
	{
		newInfo[i] = this->info[i];
		newLeft[i] = this->left[i];
		newRight[i] = this->right[i];
		newParent[i] = this->parent[i];
	}

	for (int i = this->capacity; i < this->capacity * 2; i++)
	{
		newInfo[i] = NULL_TELEM;
		newLeft[i] = i + 1;
		newRight[i] = -11;
		newParent[i] = -11;
	}
	newLeft[this->capacity * 2 - 1] = -1;

	this->firstEmpty = this->capacity;
	this->capacity *= 2;
	delete[] this->info;
	delete[] this->left;
	delete[] this->right;
	delete[] this->parent;
	this->info = newInfo;
	this->left = newLeft;
	this->right = newRight;
	this->parent = newParent;
}