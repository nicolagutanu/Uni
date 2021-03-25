#pragma once
#include "domain.h"

class Repository
{
private:
	std::string fileName;

	//function that receives a line and a delimiter and break the line up in the fileds of an Item
	vector<string> convertLine(string line, char delimiter);
	//function that reads information from file and returnes a list with all Items
	vector<Vegetable> readFromFile();
	//function that writes all info gathered in repo to file
	void writeToFile(vector<Vegetable> allTasks);

public:
	//constructor
	Repository(const string& fileName);

	void addVegetable(const Vegetable& vegToAdd);
	void deleteVegetable(const Vegetable& vegToDetele);
	void updateVegetable(const Vegetable& newVeg);

	int vegExistsInList(const Vegetable& veg);

	vector<Vegetable> getVegetables();
};

