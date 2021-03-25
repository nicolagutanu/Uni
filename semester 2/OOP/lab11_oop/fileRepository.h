#pragma once
#include "repository.h"
#include "domain.h"
#include <string>
#include <vector>

using namespace std;

class FileRepository : public Repository
{
private:
	string fileName;
	int currentPosition;

	//read, convert, write from/to file
	vector<string> convertLine(string line, char delimiter);
	vector<Plant> readFromFile();
	void writeToFile(vector<Plant> plants);

public:
	/* Constructor */
	FileRepository(const string& fileName);

	// ADMIN 
	void addPlant(const Plant& plantToAdd);
	void deletePlant(const Plant& plantToDelete);
	void updatePlant(const Plant& newPlant);
	vector<Plant> getPlants();

	//useful functions
	Plant findPlant(Plant& plantToFind);
	int plantExistsInList(const Plant& plant);
	
	// ASSISTANT
	Plant nextPlant();

	//empty, doesn't do anything
	void openPath();
};