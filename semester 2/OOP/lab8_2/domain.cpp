#define _CRTDBG_MAP_ALLOC
#include "domain.h"
#include <string>
#include <crtdbg.h>
#include <iostream>

/* Default constructor */
Plant::Plant()
{
	this->codedName = "";
	this->species = "";
	this->ageInMonths = 0;
	this->digitizedScan = "";
}

/* Constructor with parameters */
Plant::Plant(const std::string& codedName, const std::string& species, const int& ageInMonths, const std::string& digitizedScan)
{
	this->codedName = codedName;
	this->species = species;
	this->ageInMonths = ageInMonths;
	this->digitizedScan = digitizedScan;
}

/* Equality operator overwrite */
bool Plant::operator==(const Plant& plant)
{
	if (this->codedName == plant.get_codedName())
		return true;
	return false;
}

/* For printing function */
std::string Plant::plantToString()
{
	return this->get_codedName() + " - " + this->get_species() + ", " + std::to_string(this->get_ageInMonths()) + ", " + this->get_digitizedScan() + "\n";
}