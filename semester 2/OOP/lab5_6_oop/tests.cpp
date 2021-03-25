#include "dynamicVector.h"
#include "repository.h"
#include "domain.h"
#include "service.h"
#include <assert.h>
#include <iostream>

using namespace std;

void plantConstructor_emptyConstructor_codedNameIsEmpty()
{
	Plant testPlant{};
	assert(testPlant.get_codedName() == "");
}

void plantConstructor_emptyConstructor_speciesIsEmpty()
{
	Plant testPlant{};
	assert(testPlant.get_species() == "");
}

void plantConstructor_emptyConstructor_ageInMonthsIsZero()
{
	Plant testPlant{};
	assert(testPlant.get_ageInMonths() == 0);
}

void plantConstructor_emptyConstructor_digitizedScanIsEmpty()
{
	Plant testPlant{};
	assert(testPlant.get_digitizedScan() == "");
}

void plantConstructor_parametersConstructor_codedNameHasCharValue()
{
	Plant testPlant{ "I300", "Tulips", 3, "abcdefg" };
	assert(testPlant.get_codedName() == "I300");
}

void plantConstructor_parametersConstructor_speciesHasCharValue()
{
	Plant testPlant{ "I300", "Tulips", 3, "abcdefg" };
	assert(testPlant.get_species() == "Tulips");
}

void plantConstructor_parametersConstructor_ageInMonthsHasIntValue()
{
	Plant testPlant{ "I300", "Tulips", 3, "abcdefg" };
	assert(testPlant.get_ageInMonths() == 3);
}

void plantConstructor_parametersConstructor_digitizedScanHasCharValue()
{
	Plant testPlant{ "I300", "Tulips", 3, "abcdefg" };
	assert(testPlant.get_digitizedScan() == "abcdefg");
}

void dynamicArrayConstructor_emptyConstructor_getLength()
{
	DynamicVector testVector{};
	assert(testVector.getLength() == 0);
}

void addPlant_validParameters_willBeAddedToList()
{
	Repository testRepo{};
	Service testService{ testRepo };
	testService.addPlantService("I3001", "Roses", 5, "abc");
	assert(testService.getLengthService() == 1);
}

void deletePlant_validParameters_willBeDeletedFromList()
{
	Repository testRepo{};
	Service testService{ testRepo };
	testService.deletePlantService("I3001");
	assert(testService.getLengthService() == 0);	
}

void getLength_validParameters_returnCorrectLength()
{
	Repository testRepo{};
	Service testService{ testRepo };
	testService.addPlantService("I200", "abc", 1, "def");
	testService.addPlantService("I201", "ab", 2, "de");
	testService.addPlantService("I202", "a", 3, "d");
	assert(testService.getLengthService() == 3);
}

void run_tests()
{
	plantConstructor_emptyConstructor_codedNameIsEmpty();
	plantConstructor_emptyConstructor_speciesIsEmpty();
	plantConstructor_emptyConstructor_ageInMonthsIsZero();
	plantConstructor_emptyConstructor_digitizedScanIsEmpty();
	plantConstructor_parametersConstructor_codedNameHasCharValue();
	plantConstructor_parametersConstructor_speciesHasCharValue();
	plantConstructor_parametersConstructor_ageInMonthsHasIntValue();
	plantConstructor_parametersConstructor_digitizedScanHasCharValue();
	dynamicArrayConstructor_emptyConstructor_getLength();
	addPlant_validParameters_willBeAddedToList();
	deletePlant_validParameters_willBeDeletedFromList();
	getLength_validParameters_returnCorrectLength();
}