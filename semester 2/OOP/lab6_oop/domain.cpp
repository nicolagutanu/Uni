#include "domain.h"
#include <string>
#include <iostream>

/* Default constructor */
Plant::Plant() : codedName(""), species(""), ageInMonths(0), digitizedScan("") {}

/* Constructor with parameters */
Plant::Plant(const std::string& codedName, const std::string& species, const int& ageInMonths, const std::string& digitizedScan)
{
	this->codedName = codedName;
	this->species = species;
	this->ageInMonths = ageInMonths;
	this->digitizedScan = digitizedScan;
}

std::string Plant::plantToString()
{
	return this->get_codedName() + " - " + this->get_species() + ", " + std::to_string(this->get_ageInMonths()) + ", " + this->get_digitizedScan() + "\n";
}