#define _CRTDBG_MAP_ALLOC
#include <crtdbg.h>
#include "service.h"
#include <vector>
#include <stdlib.h>
#include <algorithm>

using namespace std;

/* ADMIN */
int Service::addPlantService(const std::string& codedName, const std::string& species, const int& ageInMonths, const std::string& digitizedScan)
{
	Plant plantToAdd = Plant(codedName, species, ageInMonths, digitizedScan);
	if (!this->current_repository.plantExistsInList(plantToAdd))
	{
		this->current_repository.addPlant(plantToAdd);
		return 1;
	}
	return 0;
}

int Service::deletePlantService(const std::string& codedName_of_plantToDelete)
{
	Plant plantToDelete = Plant(codedName_of_plantToDelete, "", 0, "");
	if (this->current_repository.plantExistsInList(plantToDelete))
	{
		this->current_repository.deletePlant(plantToDelete);
		return 1;
	}
	return 0;
}

int Service::updatePlantService(const std::string& codedName, const std::string& newSpecies, const int& newAgeInMonths, const std::string& newDigitizedScan)
{
	Plant newPlant = Plant(codedName, newSpecies, newAgeInMonths, newDigitizedScan);
	if (this->current_repository.plantExistsInList(newPlant))
	{
		this->current_repository.updatePlant(newPlant);
		return 1;
	}
	return 0;
}

vector<Plant> Service::getPlantsService() const
{
	return this->current_repository.getPlants();
}

/* ASSISTANT */
int Service::addPlantDisplacedListService(const std::string& codedName_of_plantToDisplace)
{
	Plant plantToDisplace = Plant(codedName_of_plantToDisplace, "", 0, "");
	if (this->current_repository.plantExistsInList(plantToDisplace) && !this->current_repository.plantExistsInDisplacedList(plantToDisplace))
	{
		this->current_repository.addPlantDisplacedList(plantToDisplace);
		return 1;
	}
	return 0;
}

Plant Service::nextPlantService()
{
	return this->current_repository.nextPlant();
}

vector<Plant> Service::getFilteredPlantsService(const string& givenSpecies, int givenAgeInMonths)
{
	vector<Plant> plants = current_repository.getPlants();
	if (givenSpecies == "")
		return plants;
	vector<Plant> filteredPlants(plants.size());
	auto filterFunction = [&givenSpecies, &givenAgeInMonths](const Plant& plant)
	{
		return plant.get_species() == givenSpecies && plant.get_ageInMonths() == givenAgeInMonths;
	};
	auto it = copy_if(plants.begin(), plants.end(), filteredPlants.begin(), filterFunction);
	filteredPlants.resize(distance(filteredPlants.begin(), it));
}

vector<Plant> Service::getPlantsToBeDisplacedService()
{
	return this->current_repository.getPlantsToBeDisplaced();
}