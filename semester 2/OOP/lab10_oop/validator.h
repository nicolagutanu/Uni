#pragma once
#include "exceptions.h"
#include <string>

class Validator
{
public:
	Validator() {};
	void check_ageInMonths_IsNumber(std::string& toCheck);
	void check_ageInMonths_positiveNumber(int toCheck);
	void check_itsNotEmpty(std::string& toCheck);
};