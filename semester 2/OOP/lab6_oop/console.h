#pragma once
#include "service.h"
#include <iostream>

class Console
{
private:
	Service console;

	//ADMIN
	/* Printing function for the admin menu */
	static void adminMode();

	void addPlant(const std::string& codedName, const std::string& species, const int& ageInMonths, const std::string& digitizedScan);
	void deletePlant(const std::string& codedName_of_plantToDelete);
	void updatePlant(const std::string& codedName, const std::string& newSpecies, const int& newAgeInMonths, const std::string& newDigitizedScan);
	void listPlants();

	/* Function that calls all functions used by the admin */
	void modeA();

	//ASSISNTANT
	/* Printing function for the assistant menu */
	static void assistantMode();

	void addPlantToDisplacedList(const std::string& codedName);
	void listBySpeciesAndAge(const std::string species, const int& ageInMonths);
	void listDisplacedList();
	int next(int positionInAdminList);

	/* Function that calls all functions used by the admin */
	void modeB();

public:
	/* Constructor */
	Console(const Service& service) : console(service) {}

	/* Fnction that runs the show */
	void run();
};