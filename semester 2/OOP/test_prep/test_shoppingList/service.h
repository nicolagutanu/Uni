#pragma once
#include "repository.h"
#include "domain.h"
#include <vector>

using namespace std;

class Service
{
private:
	FileRepository* current_repository;

public:
	//constructor
	Service(FileRepository* repository) : current_repository{ repository } {}

	int addItemService(const std::string& category, const std::string& name, const int quantity);
	int deleteItemService(const std::string category, const std::string name, const int quantity);
	int updateItemService(const std::string category, const std::string name, const int quantity);
	
	//function that returns a list of filtered items by category
	vector<Item> filteredPlants(const std::string category);

	vector<Item> getItemsService() const;
};

