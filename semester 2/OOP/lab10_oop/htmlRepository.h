#pragma once
#include "repository.h"
#include <string>

using namespace std;

class HtmlRepository : public Repository
{
private:
	string fileName;

	vector<Plant> readFromFile();
	void writeToFile(vector<Plant> plants);

public:
	/* Constructor */
	HtmlRepository(const string& fileName);

	// ADMIN 
	void addPlant(const Plant& plantToAdd);
	void deletePlant(const Plant& plantToDelete);
	void updatePlant(const Plant& newPlant);

	int plantExistsInList(const Plant& plant);
	vector<Plant> getPlants();

	Plant findPlant(Plant& plantToFind);
	Plant nextPlant();

	void openPath();
};