#include "repository.h"
#include <cstring>
#include <sstream>
#include <fstream>

vector<string> FileRepository::convertLine(string line, char delimiter)
{
	stringstream stringStream(line);
	string subString;
	vector<string> item;
	while (getline(stringStream, subString, delimiter))
		item.push_back(subString);
	return item;
}

vector<Item> FileRepository::readFromFile()
{
	std::string item;
	ifstream fileIn;
	fileIn.open(this->fileName);
	vector<std::string> Items;
	vector<Item> allItems;
	Item newItem;
	while (getline(fileIn, item))
	{
		Items = this->convertLine(item, ',');
		newItem = Item(Items[0], Items[1], stoi(Items[2]));
		allItems.push_back(newItem);
	}
	fileIn.close();
	return allItems;
}

void FileRepository::writeToFile(vector<Item> allItems)
{
	ofstream fileOut(this->fileName);
	for (Item item : allItems)
	{
		fileOut << item.get_category() << ',' << item.get_name() << ',' << std::to_string(item.get_quantity()) << '\n';
	}
}

FileRepository::FileRepository(const string& fileName)
{
	this->fileName = fileName;
}

void FileRepository::addItem(const Item& itemToAdd)
{
	vector<Item> items = this->readFromFile();
	items.push_back(itemToAdd);
	this->writeToFile(items);
}

void FileRepository::deleteItem(const Item& itemToDelete)
{
	vector<Item> items = this->readFromFile();
	vector<Item>::iterator it = find(items.begin(), items.end(), itemToDelete);
	if (it != items.end())
		items.erase(it);
	this->writeToFile(items);
}

void FileRepository::updateItem(const Item& newItem)
{
	vector<Item> items = this->readFromFile();
	vector<Item>::iterator it = find(items.begin(), items.end(), newItem);
	if (it != items.end())
		*it = newItem;
	this->writeToFile(items);
}

int FileRepository::itemExistsInList(const Item& item)
{
	vector<Item> items = this->readFromFile();
	vector<Item>::iterator it = find(items.begin(), items.end(), item);
	if (it != items.end())
		return 1;
	return 0;
}

vector<Item> FileRepository::getItems()
{
	vector<Item> items = this->readFromFile(); 
	return items;
}
