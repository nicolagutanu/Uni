#pragma once
#include "repository.h"
#include <iostream>

class Service
{
private:
	Repository current_repository;

public:
	/* Constructor */
	Service(Repository& repository) : current_repository{ repository } {}

	// ADMIN
	int addPlantService(const std::string& codedName, const std::string& species, const int& ageInMonths, const std::string& digitizedScan);
	int deletePlantService(const std::string& codedName_of_plantToDelete);
	int updatePlantService(const std::string& codedName, const std::string& newSpecies, const int& newAgeInMonths, const std::string& newDigitizedScan);
	
	int getLengthService();
	Plant getPlantByPositionService(const int& position);
	Plant* getPlantsService();

	//ASSISTANT
	int addPlantDisplacedListService(const std::string& codedName_of_plantToDisplace);
	int getLengthOfDisplacedListService();
	Plant getPlantFromDisplacedListByPositionService(const int& position);
	Plant* getPlantsToBeDisplacedService();
};