#pragma once
#include "service.h"
#include <iostream>

class Console
{
private:
	Service console;

	/* Printing functions for the menus */
	static void adminMode();
	static void assistantMode();

	void addPlant(const std::string& codedName, const std::string& species, const int& ageInMonths, const std::string& digitizedScan);
	void deletePlant(const std::string& codedName_of_plantToDelete);
	void updatePlant(const std::string& codedName, const std::string& newSpecies, const int& newAgeInMonths, const std::string& newDigitizedScan);
	void listPlants();

public:
	/* Constructor */
	Console(const Service& service) : console(service) {};
	/* Fnction that does everything */
	void run();
};