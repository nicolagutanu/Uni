#include "Repository.h"
#include <sstream>
#include <fstream>
#include <cstring>
#include <algorithm>

std::vector<string> Repository::convertLineS(string line, char delimiter)
{
	stringstream stringStream(line);
	string subString;
	vector<string> q;
	while (getline(stringStream, subString, delimiter))
		q.push_back(subString);
	return q;
}

std::vector<Star> Repository::readFromFileS()
{
	std::string q;
	ifstream fileIn;
	fileIn.open(this->fileNameS);
	vector<std::string> Questions;
	vector<Star> allQuestions;
	Star newQuestion;
	while (getline(fileIn, q))
	{
		Questions = this->convertLineS(q, '|');
		newQuestion = Star(Questions[0], Questions[1], stoi(Questions[2]), stoi(Questions[3]), stoi(Questions[4]));
		allQuestions.push_back(newQuestion);
	}
	fileIn.close();
	return allQuestions;
}

void Repository::writeToFileS(std::vector<Star> allSs)
{
	auto sortRuleQ = [](Star const& q1, Star const& q2) -> bool
	{
		return q1.get_con() < q2.get_con();
	};
	std::sort(allSs.begin(), allSs.end(), sortRuleQ);
	ofstream fileOut(this->fileNameS);
	for (Star q : allSs)
		fileOut << q.get_name() << "|" << q.get_con() << "|" << to_string(q.get_ra()) << "|" << to_string(q.get_dec()) << "|" << to_string(q.get_diameter()) << "\n";
}

std::vector<string> Repository::convertLineA(string line, char delimiter)
{
	stringstream stringStream(line);
	string subString;
	vector<string> q;
	while (getline(stringStream, subString, delimiter))
		q.push_back(subString);
	return q;
}

std::vector<Astronomer> Repository::readFromFileA()
{
	std::string q;
	ifstream fileIn;
	fileIn.open(this->fileNameA);
	vector<std::string> Questions;
	vector<Astronomer> allQuestions;
	Astronomer newQuestion;
	while (getline(fileIn, q))
	{
		Questions = this->convertLineA(q, '|');
		newQuestion = Astronomer(Questions[0], Questions[1]);
		allQuestions.push_back(newQuestion);
	}
	fileIn.close();
	return allQuestions;
}

Repository::Repository(const std::string fileName1, const std::string fileName2)
{
	this->fileNameA = fileName1;
	this->fileNameS = fileName2;
	this->allA = this->readFromFileA();
	this->allStars = this->readFromFileS();
}

Repository::~Repository()
{
	this->writeToFileS(this->allStars);
}

vector<Star> Repository::getStars()
{
	return this->allStars;
}

vector<Astronomer> Repository::getAstronomers()
{
	return this->allA;
}

int Repository::addStar(const Star& s)
{
	//checks to see if the name is valid, isn't empty
	if (s.get_name() == "")
		throw exception("Not valid name");
	//checks to see that diameter is valid, greater than 0
	if (s.get_diameter() <= 0)
		throw exception("Not valid diameter");
	vector<Star>::iterator it = find(this->allStars.begin(), this->allStars.end(), s);
	if (it != this->allStars.end()) //checks if the star doesn't already exist
	{
		throw exception("star already exists");
	}
	else
	{//adds the star if everything is alright
		this->allStars.push_back(s);
		return 1;
	}
}

void Repository::updateStar(const Star& s)
{
	vector<Star>::iterator it = find(this->allStars.begin(), this->allStars.end(), s);
	if (it != this->allStars.end())
		*it = s;
}

void Repository::deleteStar(const Star& s)
{
	vector<Star>::iterator it = find(this->allStars.begin(), this->allStars.end(), s);
	if (it != this->allStars.end())
		allStars.erase(it);
}

int Repository::sExistsInList(const Star& s)
{
	vector<Star>::iterator it = find(this->allStars.begin(), this->allStars.end(), s);
	if (it != this->allStars.end())
		return 1;
	return 0;
}
