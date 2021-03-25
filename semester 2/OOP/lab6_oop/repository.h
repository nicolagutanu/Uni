#pragma once
#include "dynamicVector.h"
#include <iostream>

class Repository
{
private:
	DynamicVector<Plant> listForAdmin;
	DynamicVector<Plant> displacedList;

public:

	/* Constructor */
	Repository() {}

	// ADMIN 
	/* Function the return the position of a given object */
	int findPlant(const Plant& plantToFind);

	int addPlantRepo(const Plant& plantToAdd);
	int deletePlantRepo(const Plant& plantToDelete);
	int updatePlantRepo(const Plant& newPlant, const Plant& plantToUpdate);
	
	int getLengthRepo();
	Plant getPlantByPosition(const int& position);
	Plant* getPlants();

	// ASSISTANT
	int addPlantDisplacedList(const Plant& plantToDisplace);
	int getLengthOfDisplacedList();
	Plant getPlantFromDisplacedListByPosition(const int& position);
	Plant* getPlantsToBeDisplaced();
};