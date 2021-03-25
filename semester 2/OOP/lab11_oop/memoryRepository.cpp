#include "memoryRepository.h"

vector<Plant> MemoryRepository::readFromFile()
{
	return vector<Plant>();
}

void MemoryRepository::writeToFile(vector<Plant> plants)
{
}

MemoryRepository::MemoryRepository()
{
	this->currentPosition = 0;
}

void MemoryRepository::addPlant(const Plant& plantToAdd)
{
	this->allPlants.push_back(plantToAdd);
}

void MemoryRepository::deletePlant(const Plant& plantToDelete)
{
	vector<Plant>::iterator it = find(this->allPlants.begin(), this->allPlants.end(), plantToDelete);
	if (it != this->allPlants.end())
		this->allPlants.erase(it);
}

void MemoryRepository::updatePlant(const Plant& newPlant)
{
	vector<Plant>::iterator it = find(this->allPlants.begin(), this->allPlants.end(), newPlant);
	if (it != this->allPlants.end())
		*it = newPlant;
}

vector<Plant> MemoryRepository::getPlants()
{
	return this->allPlants;
}

Plant MemoryRepository::findPlant(Plant& plantToFind)
{
	vector<Plant>::iterator it = find(this->allPlants.begin(), this->allPlants.end(), plantToFind);
	if (it != this->allPlants.end())
		return *it;
}

int MemoryRepository::plantExistsInList(const Plant& plant)
{
	vector<Plant>::iterator it = find(this->allPlants.begin(), this->allPlants.end(), plant);
	if (it != this->allPlants.end())
		return 1;
	return 0;
}

Plant MemoryRepository::nextPlant()
{
	if (this->currentPosition == this->allPlants.size())
		this->currentPosition = 0;
	Plant nextPlant = this->allPlants[this->currentPosition];
	this->currentPosition += 1;
	return nextPlant;
}

void MemoryRepository::openPath()
{
}