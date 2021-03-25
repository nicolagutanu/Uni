#include "htmlRepository.h"
#include <vector>
#include <fstream>
#include <Windows.h>

using namespace std;

vector<Plant> HtmlRepository::readFromFile()
{
	string line;
	Plant plant;
	vector<Plant> allPlants;
	ifstream fileIn;
	fileIn.open(this->fileName);
	string codedName{ " " };
	string species{ " " };
	int ageInMonths{ 0 };
	string digitizedScan{ " " };
	int uselessLinesCount = 4;
	while (getline(fileIn, line))
	{
		if (!line.compare(0, 7, "\t\t\t<td>"))
		{
			if (uselessLinesCount)
				uselessLinesCount -= 1;
			else
			{
				int startLine = line.find(">") + 1;
				int endLine = line.rfind("<") - startLine;
				codedName = line.substr(startLine, endLine);
				getline(fileIn, line);
				endLine = line.rfind("<") - startLine;
				species = line.substr(startLine, endLine);
				getline(fileIn, line);
				endLine = line.rfind("<") - startLine;
				ageInMonths = stoi(line.substr(startLine, endLine));
				getline(fileIn, line);
				endLine = line.rfind("<") - startLine;
				digitizedScan = line.substr(startLine, endLine);
				plant = Plant(codedName, species, ageInMonths, digitizedScan);
				allPlants.push_back(plant);
			}
		}
		/*if (line == "\t\t<td>\n")
		{
			if (uselessLinesCount)
				uselessLinesCount -= 1;
			else
			{
				int startLine = line.find(">") + 1;
				int endLine = line.rfind("<") - startLine;
				codedName = line.substr(startLine, endLine);
				getline(fileIn, line);
				endLine = line.rfind("<") - startLine;
				species = line.substr(startLine, endLine);
				getline(fileIn, line);
				endLine = line.rfind("<") - startLine;
				ageInMonths = stoi(line.substr(startLine, endLine));
				getline(fileIn, line);
				endLine = line.rfind("<") - startLine;
				digitizedScan = line.substr(startLine, endLine);
				plant = Plant(codedName, species, ageInMonths, digitizedScan);
				allPlants.push_back(plant);
			}
		}*/
	}
	fileIn.close();
	return allPlants;
}

void HtmlRepository::writeToFile(vector<Plant> plants)
{
	string fileContent;
	fileContent += "<!DOCTYPE html>\n";
	fileContent += "<html>\n";
	fileContent += "\t<head>\n";
	fileContent += "\t\t<title>Displaced list</title>\n";
	fileContent += "\t</head>\n";
	fileContent += "\t<body>\n";
	fileContent += "\t\t<table border=\"1\">\n";
	fileContent += "\t\t<tr>\n";
	fileContent += "\t\t\t<td>Coded Name</td>\n";
	fileContent += "\t\t\t<td>Species</td>\n";
	fileContent += "\t\t\t<td>Age in Months</td>\n";
	fileContent += "\t\t\t<td>Digitized Scan</td>\n";
	fileContent += "\t\t</tr>\n";
	for (auto plant : plants)
	{
		fileContent += "\t\t<tr>\n";
		fileContent += "\t\t\t<td>" + plant.get_codedName() + "</td>\n";
		fileContent += "\t\t\t<td>" + plant.get_species() + "</td>\n";
		fileContent += "\t\t\t<td>" + to_string(plant.get_ageInMonths()) + "</td>\n";
		fileContent += "\t\t\t<td>" + plant.get_digitizedScan() + "</td>\n";
		fileContent += "\t\t</tr>\n";
	}
	fileContent += "\t\t</table>\n";
	fileContent += "\t</body>\n";
	fileContent += "</html>";
	ofstream fileOut(this->fileName);
	fileOut << fileContent;
	fileOut.close();
}

HtmlRepository::HtmlRepository(const string& fileName)
{
	this->fileName = fileName;
}

void HtmlRepository::addPlant(const Plant& plantToAdd)
{
	vector<Plant> allPlants = this->readFromFile();
	allPlants.push_back(plantToAdd);
	this->writeToFile(allPlants);
}

void HtmlRepository::deletePlant(const Plant& plantToDelete)
{
}

void HtmlRepository::updatePlant(const Plant& newPlant)
{
}

int HtmlRepository::plantExistsInList(const Plant& plant)
{
	vector<Plant> allPlants = this->readFromFile();
	auto it = find(allPlants.begin(), allPlants.end(), plant);
	if (it != allPlants.end())
		return true;
	return false;
}

vector<Plant> HtmlRepository::getPlants()
{
	vector<Plant> allPlants = this->readFromFile();
	return allPlants;
}

Plant HtmlRepository::findPlant(Plant& plantToFind)
{
	return Plant();
}

Plant HtmlRepository::nextPlant()
{
	return Plant();
}

void HtmlRepository::openPath()
{
	std::string program_path = "\"\"C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe\" \"" + this->fileName + "\"\"";
	system(program_path.c_str());
}