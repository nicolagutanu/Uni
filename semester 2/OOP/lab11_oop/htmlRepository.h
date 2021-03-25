#pragma once
#include "repository.h"
#include <string>

using namespace std;

class HtmlRepository : public Repository
{
private:
	string fileName;

	//read, write from/to file
	vector<Plant> readFromFile();
	void writeToFile(vector<Plant> plants);

public:
	// constructor 
	HtmlRepository(const string& fileName);

	// ADMIN 
	void addPlant(const Plant& plantToAdd);
	void deletePlant(const Plant& plantToDelete);
	void updatePlant(const Plant& newPlant);
	vector<Plant> getPlants();

	//useful functions
	int plantExistsInList(const Plant& plant);
	Plant findPlant(Plant& plantToFind);
	
	//ASISSTANT
	Plant nextPlant();

	//opens html file -> chrome
	void openPath();
};