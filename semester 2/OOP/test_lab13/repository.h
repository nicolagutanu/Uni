#pragma once
#include "domain.h"
#include <vector>

class Repository
{
private:
	std::string fileName;

	//function that receives a line and a delimiter and break the line up in the fileds of an Item
	vector<string> convertLine(string line, char delimiter);
	//function that reads information from file and returnes a list with all Items
	vector<Disorder> readFromFile();
	//function that writes all info gathered in repo to file
	void writeToFile(vector<Disorder> allDisorders);

public:
	Repository(const std::string& fileName);

	vector<Disorder> getDisorders();
};