#pragma once
#include <vector>
#include "domain.h"
#include <iostream>

class Repository
{
private:
	/* List accessible only to admin */
	std::vector<Plant> listForAdmin{};
	/* List accessible only to asisstant*/
	std::vector<Plant> displacedList{};
	/* Current position in list for nextPlant() function */
	int currentPosition;

public:

	// ADMIN 
	virtual void addPlant(const Plant& plantToAdd) = 0;
	virtual void deletePlant(const Plant& plantToDelete) = 0;
	virtual void updatePlant(const Plant& newPlant) = 0;

	virtual int plantExistsInList(const Plant& plant) = 0;
	virtual std::vector<Plant> getPlants() = 0;

	// ASSISTANT
	virtual void addPlantDisplacedList(const Plant& plantToDisplace) = 0;
	virtual Plant nextPlant() = 0;
	virtual int plantExistsInDisplacedList(const Plant& plant) = 0;
	virtual std::vector<Plant> getPlantsToBeDisplaced() = 0;
};