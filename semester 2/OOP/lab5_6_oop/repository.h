#pragma once
#include "dynamicVector.h"
#include <iostream>

class Repository
{
private:
	DynamicVector plants;

public:

	/* Constructor */
	Repository() {};

	/* Function the return the position of a given object */
	int findPlant(const Plant& plantToFind);

	bool addPlantRepo(const Plant& plantToAdd);
	bool deletePlantRepo(const Plant& plantToDelete);
	bool updatePlantRepo(const Plant& newPlant, const Plant& plantToUpdate);
	int getLengthRepo();
	Plant getPlantByPosition(const int& position);
	Plant* getPlants();
};