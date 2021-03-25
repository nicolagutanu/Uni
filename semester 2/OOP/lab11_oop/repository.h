#pragma once
#include <vector>
#include "domain.h"
#include <iostream>

using namespace std;

//VIRTUAL REPOSITORY
class Repository
{
protected:
	//read, write from/to file
	virtual vector<Plant> readFromFile() = 0;
	virtual void writeToFile(vector<Plant>) = 0;

public:
	// ADMIN 
	virtual void addPlant(const Plant& plantToAdd) = 0;
	virtual void deletePlant(const Plant& plantToDelete) = 0;
	virtual void updatePlant(const Plant& newPlant) = 0;
	virtual std::vector<Plant> getPlants() = 0;

	//useful functions
	virtual int plantExistsInList(const Plant& plant) = 0;
	virtual Plant findPlant(Plant& plantToFind) = 0;
	
	//ASISSTANT
	virtual Plant nextPlant() = 0;

	//opens file
	virtual void openPath() = 0;
};