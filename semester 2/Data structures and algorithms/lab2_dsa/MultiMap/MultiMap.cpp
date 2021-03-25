#include "MultiMap.h"
#include "MultiMapIterator.h"
#include <exception>
#include <iostream>

using namespace std;


MultiMap::MultiMap() 
{
	// Theta(1)
	//TODO - Implementation
	this->elems = NULL;
	this->length = 0;
}


void MultiMap::add(TKey c, TValue v) 
{
	// O(n), where n is the length of the keys linked list
	//TODO - Implementation
	int size_vector = this->search(c).size();
	if (size_vector == 0)
	{
		Keys* new_key = new Keys;
		new_key->key = c;
		new_key->nextKey = this->elems;
		new_key->headValue = new Node;
		new_key->headValue->next = NULL;
		new_key->headValue->value = v;
		this->elems=new_key;
	}
	else
	{
		Keys* current_key = this->elems;
		while (current_key->key != c)
			current_key = current_key->nextKey;
		Node* new_node = new Node;
		new_node->next = current_key->headValue;
		new_node->value = v;
		current_key->headValue = new_node;
	}
	this->length += 1;

}


bool MultiMap::remove(TKey c, TValue v) 
{
	// O(m), where m is the length
	//TODO - Implementation
	int size_vector = this->search(c).size();
	if (size_vector == 0)
		return false;
	Keys* current_key = this->elems;
	Keys* previous_key = NULL;
	Keys* next_key;
	while (current_key->key != c)
	{
		previous_key = current_key;
		current_key = current_key->nextKey;
	}
	next_key = current_key->nextKey;
	Node* current_node = current_key->headValue;
	Node* previous_node = NULL;
	Node* next_node;
	while (current_node->value != v && current_node->next != NULL)
	{
		previous_node = current_node;
		current_node = current_node->next;
	}
	next_node = current_node->next;
	if (current_node->value != v)
		return false;
	if (current_node == current_key->headValue)
	{
		delete current_node;
		current_key->headValue = next_node;
		if (current_key->headValue == NULL)
		{
			if (current_key == this->elems)
			{
				delete current_key;
				this->elems = next_key;
			}
			else
			{
				delete current_key;
				previous_key->nextKey = next_key;
			}
		}
	}
	else
	{
		delete current_node;
		previous_node->next = next_node;
	}
	this->length -= 1;
	return true;
}


vector<TValue> MultiMap::search(TKey c) const 
{
	// Theta(n), where n is the length of the key linked list c
	//TODO - Implementation
	vector<TValue> values_of_key;
	Keys* current_key = this->elems;
	if (current_key != NULL)
	{
		while (current_key != NULL && current_key->key != c )
			current_key = current_key->nextKey;
		if (current_key == NULL)
			return values_of_key;
		Node* current_node = current_key->headValue;
		while (current_node != NULL)
		{
			values_of_key.push_back(current_node->value);
			current_node = current_node->next;
		}
	}
	return values_of_key;
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
	else
		return false;
}

MultiMapIterator MultiMap::iterator() const 
{
	// Theta(1)
	return MultiMapIterator(*this);
}


MultiMap::~MultiMap() 
{
	// O(n*m), where n is the length of the keys linked list and m is the length of each of the value lists
	//TODO - Implementation
	Keys* current_key = this->elems;
	Keys* next_key;
	while (current_key != NULL)
	{
		next_key = current_key->nextKey;
		Node* current_node = current_key->headValue;
		Node* next_node;
		while (current_node != NULL)
		{
			next_node = current_node->next;
			delete current_node;
			current_node = next_node;
		}
		delete current_key;
		current_key = next_key;
	}
	this->elems = NULL;
}

vector<TKey> MultiMap::keySet() const
{
	//best = Theta(1), worst = Theta(n) => overall = O(n), where n is the number of nodes in the keys list
	vector<TKey> vector_of_keys;
	Keys* current_key = this->elems;
	if (current_key != NULL)
	{
		while (current_key != NULL)
		{
			vector_of_keys.push_back(current_key->key);
			current_key = current_key->nextKey;
		}
	}
	return vector_of_keys;
}