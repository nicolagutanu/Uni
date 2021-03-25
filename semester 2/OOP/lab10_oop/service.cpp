#define _CRTDBG_MAP_ALLOC
#include <crtdbg.h>
#include "service.h"
#include <vector>
#include <stdlib.h>
#include <algorithm>

using namespace std;

Service::~Service()
{
	delete mylist;
}

/* ADMIN */
int Service::addPlantService(const std::string& codedName, const std::string& species, const int& ageInMonths, const std::string& digitizedScan)
{
	Plant plantToAdd = Plant(codedName, species, ageInMonths, digitizedScan);
	if (this->current_repository->plantExistsInList(plantToAdd) == true)
		throw RepoException("The plant already exists!\n");
	this->current_repository->addPlant(plantToAdd);
}

int Service::deletePlantService(const std::string& codedName_of_plantToDelete)
{
	Plant plantToDelete = Plant(codedName_of_plantToDelete, "", 0, "");
	if (this->current_repository->plantExistsInList(plantToDelete) == false)
		throw RepoException("The plant doesn't exist!\n");
	this->current_repository->deletePlant(plantToDelete);
}

int Service::updatePlantService(const std::string& codedName, const std::string& newSpecies, const int& newAgeInMonths, const std::string& newDigitizedScan)
{
	Plant newPlant = Plant(codedName, newSpecies, newAgeInMonths, newDigitizedScan);
	if (this->current_repository->plantExistsInList(newPlant) == false)
		throw exception("The plant doesn't exist!\n");
	this->current_repository->updatePlant(newPlant);
}

vector<Plant> Service::getPlantsService() const
{
	return this->current_repository->getPlants();
}

/* ASSISTANT */
int Service::addPlantDisplacedListService(const std::string& codedName_of_plantToDisplace)
{
	Plant plant = Plant(codedName_of_plantToDisplace, "", 0, "");
	Plant plantToDisplace = this->current_repository->findPlant(plant);
	if (this->mylist->plantExistsInList(plantToDisplace) == true)
		throw RepoException("This plant already exists!\n");
	this->mylist->addPlant(plantToDisplace);
}

Plant Service::nextPlantService()
{
	return this->current_repository->nextPlant();
}

vector<Plant> Service::getPlantsToBeDisplacedService()
{
	return this->mylist->getPlants();
}

void Service::openPath()
{
	this->mylist->openPath();
}
