#pragma once
#include "fileRepository.h"
#include "domain.h"
#include "exceptions.h"
#include "repository.h"
#include "htmlRepository.h"
#include "csvRepository.h"
#include "memoryRepository.h"

using namespace std;

class Service
{
private:
	Repository* current_repository;
	Repository* mylist;
public:
	/* Constructor */
	Service(Repository* repository, Repository* mylist) : current_repository{ repository }, mylist{ mylist } {}

	~Service();

	// ADMIN
	int addPlantService(const std::string& codedName, const std::string& species, const int& ageInMonths, const std::string& digitizedScan);
	int deletePlantService(const std::string& codedName_of_plantToDelete);
	int updatePlantService(const std::string& codedName, const std::string& newSpecies, const int& newAgeInMonths, const std::string& newDigitizedScan);

	vector<Plant> getPlantsService() const;

	//ASSISTANT
	int addPlantDisplacedListService(const std::string& codedName_of_plantToDisplace);
	Plant nextPlantService();
	vector<Plant> getPlantsToBeDisplacedService();

	void openPath();
};