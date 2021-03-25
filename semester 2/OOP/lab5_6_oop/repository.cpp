#include "repository.h"
#include <iostream>
#include <string>
#include <stdlib.h>
#include <assert.h>

int Repository::findPlant(const Plant& plantToFind)
{
	int length = this->plants.getLength();
	for (int i = 0; i < length; i++)
	{
		if (this->plants.getElement(i).get_codedName() == plantToFind.get_codedName())
			return i;
	}
	return -1;
}

bool Repository::addPlantRepo(const Plant& plantToAdd)
{
	if (findPlant(plantToAdd) != -1)
		return false;
	this->plants.addPlantToDatabase(plantToAdd);
	return true;
}

bool Repository::deletePlantRepo(const Plant& plantToDelete)
{
	int position_of_plantToDelete = findPlant(plantToDelete);
	if (position_of_plantToDelete == -1)
		return false;
	this->plants.deletePlantFromDatabase(position_of_plantToDelete);
	return true;
}

bool Repository::updatePlantRepo(const Plant& newPlant, const Plant& plantToUpdate)
{
	int position_of_plantToUpdate = findPlant(plantToUpdate);
	if (position_of_plantToUpdate == -1)
		return false;
	this->plants.updatePlantInDatabase(newPlant, position_of_plantToUpdate);
	return true;
}

int Repository::getLengthRepo()
{
	return this->plants.getLength();
}

Plant Repository::getPlantByPosition(const int& position)
{
	return this->plants.getElement(position);
}

Plant* Repository::getPlants()
{
	return this->plants.getElements();
}