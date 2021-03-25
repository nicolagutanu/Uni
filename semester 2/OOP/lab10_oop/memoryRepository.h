#pragma once
#include <vector>
#include "repository.h"
#include "domain.h"

using namespace std;

class MemoryRepository : public Repository
{
private:
	vector<Plant> readFromFile();
	void writeToFile(vector<Plant> plants);

	std::vector<Plant> allPlants;
	std::vector<Plant> mylist;

	int currentPosition;

public:
	MemoryRepository();

	void addPlant(const Plant& plantToAdd);
	void deletePlant(const Plant& plantToDelete);
	void updatePlant(const Plant& newPlant);

	Plant findPlant(Plant& plantToFind);
	int plantExistsInList(const Plant& plant);
	vector<Plant> getPlants();

	// ASSISTANT
	Plant nextPlant();

	void openPath();
};