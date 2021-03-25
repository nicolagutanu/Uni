#include "repository.h"
#include <iostream>
#include <string>
#include <stdlib.h>
#include <assert.h>


// ADMIN
int Repository::findPlant(const Plant& plantToFind)
{
	int length = this->listForAdmin.getLength();
	for (int i = 0; i < length; i++)
	{
		Plant auxiliary_plant = this->listForAdmin.getElement(i);
		if (plantToFind.get_codedName() == auxiliary_plant.get_codedName())
			return i;
	}
	return -1;
}

int Repository::addPlantRepo(const Plant& plantToAdd)
{
	int position_of_plantToAdd = this->findPlant(plantToAdd);
	if (position_of_plantToAdd != -1)
		return 0;
	this->listForAdmin.addPlantToDatabase(plantToAdd);
	return 1;
}

int Repository::deletePlantRepo(const Plant& plantToDelete)
{
	int position_of_plantToDelete = this->findPlant(plantToDelete);
	if (position_of_plantToDelete == -1)
		return 0;
	this->listForAdmin.deletePlantFromDatabase(position_of_plantToDelete);
	return 1;
}

int Repository::updatePlantRepo(const Plant& newPlant, const Plant& plantToUpdate)
{
	int position_of_plantToUpdate = this->findPlant(plantToUpdate);
	if (position_of_plantToUpdate == -1)
		return 0;
	this->listForAdmin.updatePlantInDatabase(newPlant, position_of_plantToUpdate);
	return 1;
}

int Repository::getLengthRepo()
{
	return this->listForAdmin.getLength();
}

Plant Repository::getPlantByPosition(const int& position)
{
	return this->listForAdmin.getElement(position);
}

Plant* Repository::getPlants()
{
	return this->listForAdmin.getElements();
}


// ASSISTANT
int Repository::addPlantDisplacedList(const Plant& plantToDisplace)
{
	int position_of_plantToDisplace = this->findPlant(plantToDisplace);
	if (position_of_plantToDisplace == -1)
		return 0;
	Plant fullPlant = this->listForAdmin.getElement(position_of_plantToDisplace);
	this->displacedList.addPlantToDatabase(fullPlant);
	return 1;
}

int Repository::getLengthOfDisplacedList()
{
	return this->displacedList.getLength();
}

Plant Repository::getPlantFromDisplacedListByPosition(const int& position)
{
	return this->displacedList.getElement(position);
}

Plant* Repository::getPlantsToBeDisplaced()
{
	return this->displacedList.getElements();
}