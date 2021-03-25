#pragma once
#include "domain.h"
#include <vector>
#include <string>

using namespace std;

class FileRepository
{
private:
	string fileName;
	
	//function that receives a line and a delimiter and break the line up in the fileds of an Item
	vector<string> convertLine(string line, char delimiter);
	//function that reads information from file and returnes a list with all Items
	vector<Item> readFromFile();
	//function that writes all info gathered in repo to file
	void writeToFile(vector<Item> allItems);

public:
	//constructor
	FileRepository(const string& fileName);

	void addItem(const Item& itemToAdd);
	void deleteItem(const Item& itemToDelete);
	void updateItem(const Item& newItem);

	//function that checks that an item exists
	int itemExistsInList(const Item& item);

	//fnction that returns all items in the list
	vector<Item> getItems();
};

