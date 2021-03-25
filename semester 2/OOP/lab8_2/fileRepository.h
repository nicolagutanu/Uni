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
	vector<Plant> assistantList;
	int currentPosition;

	/* Function that reads a line from file and returns a vector containing the attributes of a plant */
	vector<string> convertLine(string Plant, char Delimiter);
	/* Function that reads a file line by line and returns a vector of all plants */
	vector<Plant> readFromFile();
	/* Function that receives a vector and writes its content in a file */
	void writeToFile(vector<Plant>);

public:
	/* Constructor */
	FileRepository(const string& fileName);

	// ADMIN 
	void addPlant(const Plant& plantToAdd);
	void deletePlant(const Plant& plantToDelete);
	void updatePlant(const Plant& newPlant);

	int plantExistsInList(const Plant& plant);
	vector<Plant> getPlants();

	// ASSISTANT
	void addPlantDisplacedList(const Plant& plantToDisplace);
	Plant nextPlant();
	int plantExistsInDisplacedList(const Plant& plant);
	vector<Plant> getPlantsToBeDisplaced();
};