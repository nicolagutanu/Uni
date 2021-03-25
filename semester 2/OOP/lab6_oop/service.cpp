#include "service.h"
#include <stdlib.h>
#include <iostream>


//ADMIN
int Service::addPlantService(const std::string& codedName, const std::string& species, const int& ageInMonths, const std::string& digitizedScan)
{
	Plant plantToAdd{ codedName, species, ageInMonths, digitizedScan };
	return this->current_repository.addPlantRepo(plantToAdd);
}

int Service::deletePlantService(const std::string& codedName_of_plantToDelete)
{
	Plant plantToDelete{ codedName_of_plantToDelete, "", 0, "" };
	return this->current_repository.deletePlantRepo(plantToDelete);
}

int Service::updatePlantService(const std::string& codedName, const std::string& newSpecies, const int& newAgeInMonths, const std::string& newDigitizedScan)
{
	Plant newPlant{ codedName, newSpecies, newAgeInMonths, newDigitizedScan };
	Plant plantBeforeUpdate{ codedName, "", 0, "" };
	return this->current_repository.updatePlantRepo(newPlant, plantBeforeUpdate);
}

int Service::getLengthService()
{
	return this->current_repository.getLengthRepo();
}

Plant Service::getPlantByPositionService(const int& position)
{
	return this->current_repository.getPlantByPosition(position);
}

Plant* Service::getPlantsService()
{
	return this->current_repository.getPlants();
}


// ASSISTANT
int Service::addPlantDisplacedListService(const std::string& codedName_of_plantToDisplace)
{
	Plant plantToDisplace{ codedName_of_plantToDisplace, "", 0, "" };
	return this->current_repository.addPlantDisplacedList(plantToDisplace);
}

int Service::getLengthOfDisplacedListService()
{
	return this->current_repository.getLengthOfDisplacedList();
}

Plant Service::getPlantFromDisplacedListByPositionService(const int& position)
{
	return this->current_repository.getPlantFromDisplacedListByPosition(position);
}

Plant* Service::getPlantsToBeDisplacedService()
{
	return this->current_repository.getPlantsToBeDisplaced();
}