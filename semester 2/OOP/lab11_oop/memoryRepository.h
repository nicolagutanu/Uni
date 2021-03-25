#pragma once
#include <vector>
#include "repository.h"
#include "domain.h"

using namespace std;

class MemoryRepository : public Repository
{
private:
	//empty, don't do anything
	vector<Plant> readFromFile();
	void writeToFile(vector<Plant> plants);

	//list with all plants
	std::vector<Plant> allPlants;
	//list with asisstant plants
	std::vector<Plant> mylist;

	int currentPosition;

public:
	//constructor
	MemoryRepository();

	//ADMIN
	void addPlant(const Plant& plantToAdd);
	void deletePlant(const Plant& plantToDelete);
	void updatePlant(const Plant& newPlant);
	vector<Plant> getPlants();

	//useful functions
	Plant findPlant(Plant& plantToFind);
	int plantExistsInList(const Plant& plant);
	
	// ASSISTANT
	Plant nextPlant();

	//empty, doesn't do anything
	void openPath();
};