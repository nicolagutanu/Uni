#pragma once
#include<vector>
#include<utility>
//DO NOT INCLUDE MultiMapIterator

using namespace std;

//DO NOT CHANGE THIS PART
typedef int TKey;
typedef int TValue;
typedef std::pair<TKey, TValue> TElem;
#define NULL_TVALUE -11111;
#define NULL_TELEM pair<int,int>(-11111, -11111);
class MultiMapIterator;

// classic node that has a value and the address of the next node, the last address being NULL
struct Node
{
	TValue value;
	struct Node* next;
};

// linked list that has a key, the address of the list with values of that key (that are of the type Node) 
//and the adress of the next node of this type
struct Keys
{
	TKey key;
	struct Node* headValue;
	struct Keys* nextKey;
};

class MultiMap
{
	friend class MultiMapIterator;

private:
	//TODO - Representation
	Keys* elems;
	int length;

public:
	//constructor
	MultiMap();

	//adds a key value pair to the multimap
	void add(TKey c, TValue v);

	//removes a key value pair from the multimap
	//returns true if the pair was removed (if it was in the multimap) and false otherwise
	bool remove(TKey c, TValue v);

	//returns the vector of values associated to a key. If the key is not in the MultiMap, the vector is empty
	vector<TValue> search(TKey c) const;

	//returns the number of pairs from the multimap
	int size() const;

	//checks whether the multimap is empty
	bool isEmpty() const;

	//returns an iterator for the multimap
	MultiMapIterator iterator() const;

	//descturctor
	~MultiMap();

	//returns a vector with all the keys from the MultiMap
	vector<TKey> keySet() const;
};

