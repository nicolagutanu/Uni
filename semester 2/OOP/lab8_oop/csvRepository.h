#pragma once
#include "repository.h"
#include <vector>

using namespace std;

class CsvRepository : public Repository
{
private:
	string fileName;

	vector<string> convertLine(string line, char delimiter);
	vector<Plant> readFromFile();
	void writeToFile(vector<Plant> plants);

public:
	CsvRepository(const string fileName);

	// ADMIN 
	void addPlant(const Plant& plantToAdd);
	void deletePlant(const Plant& plantToDelete);
	void updatePlant(const Plant& newPlant);

	int plantExistsInList(const Plant& plant);
	vector<Plant> getPlants();

	void openPath();
};