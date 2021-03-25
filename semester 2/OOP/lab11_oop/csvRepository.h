#pragma once
#include "repository.h"
#include <vector>

using namespace std;

class CsvRepository : public Repository
{
private:
	string fileName;

	//read, convert, write from/to file
	vector<string> convertLine(string line, char delimiter);
	vector<Plant> readFromFile();
	void writeToFile(vector<Plant> plants);

public:
	//constructors
	CsvRepository();
	CsvRepository(const string fileName);

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

	//open csv file
	void openPath();
};