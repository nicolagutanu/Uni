#include "csvRepository.h"
#include <sstream>
#include <iostream>
#include <fstream>
#include <vector>
#include <Windows.h>

using namespace std;

vector<string> CsvRepository::convertLine(std::string line, char delimiter)
{
	stringstream stringStream(line);
	string substring;
	vector<string> plant;
	while (getline(stringStream, substring, delimiter))
		plant.push_back(substring);
	return plant;
}

/* Function that reads a file line by line and returns a vector of all plants */
std::vector<Plant> CsvRepository::readFromFile()
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
		newPlant = Plant(Plants[0], Plants[1], stoi(Plants[2]), Plants[3]); //codedName, 
		allPlants.push_back(newPlant);
	}
	fileIn.close();
	return allPlants;
}

void CsvRepository::writeToFile(std::vector<Plant> plants)
{
	ofstream fileOut(this->fileName);
	for (auto plant : plants)
	{
		fileOut << plant.get_codedName() << ',' << plant.get_species() << ',' << std::to_string(plant.get_ageInMonths()) << ',' << plant.get_digitizedScan() << '\n';
	}
}


CsvRepository::CsvRepository()
{
	this->fileName = "";
}

CsvRepository::CsvRepository(const string fileName)
{
	this->fileName = fileName;
}

void CsvRepository::addPlant(const Plant& plantToAdd)
{
	vector<Plant> allPlants = this->readFromFile();
	allPlants.push_back(plantToAdd);
	this->writeToFile(allPlants);
}

void CsvRepository::deletePlant(const Plant& plantToDelete)
{
}

void CsvRepository::updatePlant(const Plant& newPlant)
{
}

int CsvRepository::plantExistsInList(const Plant& plant)
{
	vector<Plant> allPlants = this->readFromFile();
	auto it = find(allPlants.begin(), allPlants.end(), plant);
	if (it != allPlants.end())
		return true;
	return false;
}

vector<Plant> CsvRepository::getPlants()
{
	vector<Plant> allPlants = this->readFromFile();
	return allPlants;
}

Plant CsvRepository::findPlant(Plant& plantToFind)
{
	return Plant();
}

Plant CsvRepository::nextPlant()
{
	return Plant();
}

void CsvRepository::openPath()
{
	string program_path = "\"\"C:\\Users\\Carina\\AppData\\Roaming\\Microsoft\\Windows\\Start Menu\\Programs\\Accessories\\Notepad.lnk\" \"" + this->fileName + "\"\"";
	system(program_path.c_str());
}
