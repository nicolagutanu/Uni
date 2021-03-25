#include "repository.h"
#include <cstring>
#include <sstream>
#include <fstream>

vector<string> Repository::convertLine(string line, char delimiter)
{
	stringstream stringStream(line);
	string subString;
	vector<string> veg;
	while (getline(stringStream, subString, delimiter))
		veg.push_back(subString);
	return veg;
}

vector<Vegetable> Repository::readFromFile()
{
	std::string veg;
	ifstream fileIn;
	fileIn.open(this->fileName);
	vector<std::string> Vegetables;
	vector<Vegetable> allVegetables;
	Vegetable newVegetable;
	while (getline(fileIn, veg))
	{
		Vegetables = this->convertLine(veg, '|');
		newVegetable = Vegetable(Vegetables[0], Vegetables[1], Vegetables[2]);
		allVegetables.push_back(newVegetable);
	}
	fileIn.close();
	return allVegetables;
}

void Repository::writeToFile(vector<Vegetable> allTasks)
{
	ofstream fileOut(this->fileName);
	for (Vegetable veg : allTasks)
	{
		fileOut << veg.get_family() << '|' << veg.get_name() << '|' << veg.get_parts() << '\n';
	}
}

Repository::Repository(const string& fileName)
{
	this->fileName = fileName;
}

void Repository::addVegetable(const Vegetable& vegToAdd)
{
	vector<Vegetable> allVegetables = this->readFromFile();
	allVegetables.push_back(vegToAdd);
	this->writeToFile(allVegetables);
}

void Repository::deleteVegetable(const Vegetable& vegToDelete)
{
	vector<Vegetable> allVegetables = this->readFromFile();
	vector<Vegetable>::iterator it = find(allVegetables.begin(), allVegetables.end(), vegToDelete);
	if (it != allVegetables.end())
		allVegetables.erase(it);
	this->writeToFile(allVegetables);
}

void Repository::updateVegetable(const Vegetable& newVeg)
{
	vector<Vegetable> allVegetables = this->readFromFile();
	vector<Vegetable>::iterator it = find(allVegetables.begin(), allVegetables.end(), newVeg);
	if (it != allVegetables.end())
		*it = newVeg;
	this->writeToFile(allVegetables);
}

int Repository::vegExistsInList(const Vegetable& veg)
{
	vector<Vegetable> allVegetables = this->readFromFile();
	vector<Vegetable>::iterator it = find(allVegetables.begin(), allVegetables.end(), veg);
	if (it != allVegetables.end())
		return 1;
	return 0;
}

vector<Vegetable> Repository::getVegetables()
{
	vector<Vegetable> allVegetables = this->readFromFile();
	return allVegetables;
}
