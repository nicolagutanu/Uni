#pragma once
#include "repository.h"
#include <iostream>

class Service
{
private:
	Repository current_repository;

public:
	/* Constructor */
	Service(Repository& repository) : current_repository{ repository } {};
	
	bool addPlantService(const std::string& codedName, const std::string& species, const int& ageInMonths, const std::string& digitizedScan);
	bool deletePlantService(const std::string& codedName_of_plantToDelete);
	bool updatePlantService(const std::string& codedName, const std::string& newSpecies, const int& newAgeInMonths, const std::string& newDigitizedScan);
	int getLengthService();
	Plant* getPlantsService();
};