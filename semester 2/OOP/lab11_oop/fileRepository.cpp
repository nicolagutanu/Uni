#include "fileRepository.h"
#include <cstring>
#include <sstream>
#include <fstream>

using namespace std;

vector<string> FileRepository::convertLine(std::string line, char delimiter)
{
	stringstream stringStream(line);
	string substring;
	vector<string> plant;
	while (getline(stringStream, substring, delimiter))
		plant.push_back(substring);
	return plant;
}

std::vector<Plant> FileRepository::readFromFile()
{
	std::string plant;
	ifstream fileIn;
	fileIn.open(this->fileName);
	vector<std::string> Plants;
	vector<Plant> allPlants;
	Plant newPlant;
	while (fileIn >> plant)
	{
		Plants = this->convertLine(plant, ',');
		newPlant = Plant(Plants[0], Plants[1], stoi(Plants[2]), Plants[3]);
		allPlants.push_back(newPlant);
	}
	fileIn.close();
	return allPlants;
}

void FileRepository::writeToFile(std::vector<Plant> plants)
{
	ofstream fileOut(this->fileName);
	for (auto plant : plants)
	{
		fileOut << plant.get_codedName() << ',' << plant.get_species() << ',' << std::to_string(plant.get_ageInMonths()) << ',' << plant.get_digitizedScan() << '\n';
	}
}

FileRepository::FileRepository(const std::string& fileName)
{
	this->fileName = fileName;
	this->currentPosition = 0;
}

void FileRepository::addPlant(const Plant& plantToAdd)
{
	vector<Plant> plants = this->readFromFile();
	plants.push_back(plantToAdd);
	this->writeToFile(plants);
}

void FileRepository::updatePlant(const Plant& newPlant)
{
	vector<Plant> plants = this->readFromFile();
	vector<Plant>::iterator it = find(plants.begin(), plants.end(), newPlant);
	if (it != plants.end())
	{
		*it = newPlant;
	}
	this->writeToFile(plants);
}

void FileRepository::deletePlant(const Plant& plantToDelete)
{
	vector<Plant> plants = this->readFromFile();
	vector<Plant>::iterator it = find(plants.begin(), plants.end(), plantToDelete);
	if (it != plants.end())
	{
		plants.erase(it);
	}
	this->writeToFile(plants);
}

vector<Plant> FileRepository::getPlants()
{
	vector<Plant> plants = this->readFromFile();
	return plants;
}

Plant FileRepository::findPlant(Plant& plantToFind)
{
	vector<Plant> allPlants = readFromFile();
	vector<Plant>::iterator it = find(allPlants.begin(), allPlants.end(), plantToFind);
	if (it != allPlants.end())
		return *it;
}

int FileRepository::plantExistsInList(const Plant& plant)
{
	vector<Plant> plants = this->readFromFile();
	vector<Plant>::iterator it = find(plants.begin(), plants.end(), plant);
	if (it != plants.end())
	{
		return 1;
	}
	return 0;
}

Plant FileRepository::nextPlant()
{
	vector<Plant> plants = this->readFromFile();
	if (this->currentPosition == plants.size())
		this->currentPosition = 0;
	Plant nextPlant = plants[this->currentPosition];
	this->currentPosition += 1;
	return nextPlant;
}

void FileRepository::openPath()
{
}