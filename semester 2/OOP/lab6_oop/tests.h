#pragma once
#include "service.h"
#include <assert.h>

void runTests();

// TEST PLANT
void getCodedName_validCodedName_willReturnCodedName();
void getSpecies_validSpecies_willReturnSpecies();
void getAgeInMonths_validAgeInMonths_willReturnAgeInMonths();
void getDigitizedScan_validDigitizedScan_willReturnDigitizedScan();

//TEST REPOSITORY
void findPlant_validPlant_willBeFound();
void findPlant_inexistingPlant_error();
void addPlant_validPlant_willBeAdded();
void addPlant_existingPlant_error();
void deletePlant_validPlant_willBeDeleted();
void deletePlant_inexistingPlant_error();
void updatePlant_validPlant_willBeUpdatd();
void updatePlant_inexistingPlant_error();
void getLength_isValid_willReturnLength();
void getPlantByPosition_existngPlant_willReturnPlant();
void addPlantToDisplacedList_validPlant_willBeAdded();
void addPlantToDisplacedList_inexistingPlant_willNotBeAdded();
void getLengthOfDisplacedList_isValid_willReturnLength();

//TEST SERVICE
void addPlantService_validPlant_willBeAdded();
void addPlantService_existingPlant_error();
void deletePlantService_validPlant_willBeDeleted();
void deletePlantService_inexistingPlant_error();
void updatePlantService_validPlant_willBeUpdatd();
void updatePlantService_inexistingPlant_error();