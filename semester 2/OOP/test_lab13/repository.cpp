#include "repository.h"
#include <cstring>
#include <sstream>
#include <fstream>

vector<string> Repository::convertLine(string line, char delimiter)
{
	stringstream stringStream(line);
	string subString;
	vector<string> disorder;
	while (getline(stringStream, subString, delimiter))
		disorder.push_back(subString);
	return disorder;
}

vector<Disorder> Repository::readFromFile()
{
	std::string disorder;
	ifstream fileIn;
	fileIn.open(this->fileName);
	vector<std::string> Disorders;
	vector<Disorder> allDisorders;
	Disorder newDisorder;
	while (getline(fileIn, disorder))
	{
		Disorders = this->convertLine(disorder, '|');
		vector<std::string> symp = this->convertLine(Disorders[2], ',');
		newDisorder = Disorder(Disorders[0], Disorders[1], symp);
		allDisorders.push_back(newDisorder);
	}
	fileIn.close();
	return allDisorders;
}

void Repository::writeToFile(vector<Disorder> allDisorders)
{
	ofstream fileOut(this->fileName);
	for (Disorder disorder : allDisorders)
	{
		std::string symp;
		if (disorder.get_symptoms().size() == 1)
			symp = disorder.get_symptoms()[0];
		else
			symp = disorder.get_symptoms()[0] + "," + disorder.get_symptoms()[1];
		fileOut << disorder.get_category() << "|" << disorder.get_name() << "|" << symp << "\n";
	}
}

Repository::Repository(const std::string& fileName)
{
	this->fileName = fileName;
}

vector<Disorder> Repository::getDisorders()
{
	vector<Disorder> allDisorders = this->readFromFile();
	return allDisorders;
}