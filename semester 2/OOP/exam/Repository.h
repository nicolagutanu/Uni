#pragma once
#include "Domain.h"
#include <vector>

class Repository
{
private:
	std::string fileNameA;
	std::string fileNameS;

	vector<Star> allStars;
	vector<Astronomer> allA;

	std::vector<string> convertLineS(string line, char delimiter);
	std::vector<Star> readFromFileS();
	void writeToFileS(std::vector<Star> allSs);

	std::vector<string> convertLineA(string line, char delimiter);
	std::vector<Astronomer> readFromFileA();

public:
	Repository(const std::string fileName1, const std::string fileName2);
	~Repository();

	vector<Star> getStars();
	vector<Astronomer> getAstronomers();

	//funtion that adds a star if all condtions are met
	int addStar(const Star& s);
	void updateStar(const Star& s);
	void deleteStar(const Star& s);
	int sExistsInList(const Star& s);
};

