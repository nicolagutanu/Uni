#include "service.h"
#include <stdlib.h>
#include <iostream>

bool Service::addPlantService(const std::string& codedName, const std::string& species, const int& ageInMonths, const std::string& digitizedScan)
{
	Plant plantToAdd{ codedName, species, ageInMonths, digitizedScan };
	return this->current_repository.addPlantRepo(plantToAdd);
}

bool Service::deletePlantService(const std::string& codedName_of_plantToDelete)
{
	Plant plantToDelete{ codedName_of_plantToDelete, "", 0, "" };
	return this->current_repository.deletePlantRepo(plantToDelete);
}

bool Service::updatePlantService(const std::string& codedName, const std::string& newSpecies, const int& newAgeInMonths, const std::string& newDigitizedScan)
{
	Plant newPlant{ codedName, newSpecies, newAgeInMonths, newDigitizedScan };
	Plant plantBeforeUpdate{ codedName, "", 0, "" };
	return this->current_repository.updatePlantRepo(newPlant, plantBeforeUpdate);
}

int Service::getLengthService()
{
	return this->current_repository.getLengthRepo();
}

Plant* Service::getPlantsService()
{
	return this->current_repository.getPlants();
}