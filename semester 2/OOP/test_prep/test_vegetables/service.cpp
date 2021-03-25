#include "service.h"

int Service::addVegetableService(const std::string family, const std::string name, std::string parts)
{
	Vegetable vegToAdd = Vegetable(family, name, parts);
	if (this->current_repository->vegExistsInList(vegToAdd) == true)
		throw exception("Vegetable already exists!\n");
	this->current_repository->addVegetable(vegToAdd);
}

int Service::deleteVegetableService(const std::string family, const std::string name)
{
	Vegetable vegToDelete = Vegetable(family, name, "");
	if (this->current_repository->vegExistsInList(vegToDelete) == false)
		throw exception("Vegetable doesn't exist!\n");
	this->current_repository->deleteVegetable(vegToDelete);
}

int Service::updateVegetableService(const std::string family, const std::string name, std::string parts)
{
	Vegetable vegToUpdate = Vegetable(family, name, parts);
	if (this->current_repository->vegExistsInList(vegToUpdate) == false)
		throw exception("Vegetable doesn't exist!\n");
	this->current_repository->updateVegetable(vegToUpdate);
}

vector<Vegetable> Service::getVegetablesService()
{
	return this->current_repository->getVegetables();
}

Vegetable Service::getFilteredService(const std::string name)
{
	vector<Vegetable> allVegetable = this->current_repository->getVegetables();
	Vegetable filteredVeg;
	for (Vegetable veg : allVegetable)
		if (veg.get_name() == name)
			filteredVeg = veg;
	return filteredVeg;
}
