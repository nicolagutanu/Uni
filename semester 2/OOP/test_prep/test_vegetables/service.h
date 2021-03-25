#pragma once
#include "repository.h"

class Service
{
private:
	Repository* current_repository;

public:
	//constructor
	Service(Repository* repository) : current_repository{ repository } {}

	int addVegetableService(const std::string family, const std::string name, std::string parts);
	int deleteVegetableService(const std::string family, const std::string name);
	int updateVegetableService(const std::string family, const std::string name, std::string parts);

	vector<Vegetable> getVegetablesService();
	Vegetable getFilteredService(const std::string family);
};

