#pragma once
#include <iostream>

class Plant
{
private:

	std::string codedName;
	std::string species;
	int ageInMonths;
	std::string digitizedScan;

public:
	/* Default constructor that creates the object with empty fields */
	Plant();

	/* 
	Constructor for bot type
	input: codedName is an array of characters
		   species is an array of characters
		   ageInMonths is an integer
		   digitizedScan is an array of characters
	output: a plant with those atributes
	*/
	Plant(const std::string& codedName, const std::string& species, const int& ageInMonths, const std::string& digitizedScan);

	/* Equality operator overwrite */
	bool operator==(const Plant& plant);

	/* Getters */
	std::string get_codedName() const { return codedName; };
	std::string get_species() const { return species; };
	int get_ageInMonths() const { return ageInMonths; };
	std::string get_digitizedScan() const { return digitizedScan; };

	/* Function for printing */
	std::string plantToString();
};