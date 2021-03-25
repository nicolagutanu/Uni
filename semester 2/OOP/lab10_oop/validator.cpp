#include "validator.h"

using namespace std;

void Validator::check_ageInMonths_IsNumber(std::string& toCheck)
{
	for (int i = 0; i < strlen(const_cast<char*>(toCheck.c_str())); i++)
		if (isalpha(toCheck[i]))
			throw ValidatorException("This must be a number");
}

void Validator::check_ageInMonths_positiveNumber(int toCheck)
{
	if (toCheck <= 0)
		throw ValidatorException("This must be a positive number");
}

void Validator::check_itsNotEmpty(std::string& toCheck)
{
	if (strlen(const_cast<char*>(toCheck.c_str())) <= 0)
		throw ValidatorException("No field must be empty");
}
