#pragma once
#include "service.h"
#include <iostream>
#include <string>
#include <vector>
#include "validator.h"

using namespace std;

class Console
{
private:
	Validator validator;
	Service* console;

public:
	/* Constructor */
	Console(Service* console, Validator validator);

	/* ADMIN
	   Printing function for the admin menu */
	static void adminMode();

	int addPlant(const std::string& codedName, const std::string& species, const int& ageInMonths, const std::string& digitizedScan);
	int deletePlant(const std::string& codedName_of_plantToDelete);
	int updatePlant(const std::string& codedName, const std::string& newSpecies, const int& newAgeInMonths, const std::string& newDigitizedScan);
	void listPlants();

	/* Function that calls all functions used by the admin */
	void modeA();

	/* ASSISNTANT
	   Printing function for the assistant menu */
	static void assistantMode();

	int addPlantToDisplacedList(const std::string& codedName);
	void listDisplacedList();
	void listBySpeciesAge(std::string& species, int ageInMonths);
	void next();

	/* Function that calls all functions used by the asisstant */
	void modeB();

	/* Fnction that runs the show */
	void run();
};