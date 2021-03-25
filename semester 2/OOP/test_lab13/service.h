#pragma once
#include "repository.h"

class Service
{
private:
	Repository* current_repository;

public:
	//constructor
	Service(Repository* repository) : current_repository{ repository } {}

	vector<Disorder> getDisordersService();

	//searches for disorder with that name and returns a list with the symptomps
	vector<std::string> searchDisorders(const std::string name);
};