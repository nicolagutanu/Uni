#pragma once
#include "service.h"
#include "fileRepository.h"
#include "domain.h"
#include <assert.h>

// TEST PLANT
void getCodedName_validCodedName_willReturnCodedName();
void getSpecies_validSpecies_willReturnSpecies();
void getAgeInMonths_validAgeInMonths_willReturnAgeInMonths();
void getDigitizedScan_validDigitizedScan_willReturnDigitizedScan();
void equalityOperator_notEqual_willReturnFalse();

//TEST REPOSITORY
void addPlant_validPlant_willBeAdded();
void deletePlant_validPlant_willBeDeleted();
void updatePlant_validPlant_willBeUpdatd();
void plantExistsInList_plantExists_willReturnTrue();
void plantExistsInList_plantDoesntExists_willReturnFalse();
void getPlants_notEmptyList_willReturnList();
void addPlantToDisplacedList_validPlant_willBeAdded();
void nextPlant_plantExists_willReturnNext();

//TEST SERVICE
void addPlantService_validPlant_willBeAdded();
void addPlantService_existingPlant_error();
void deletePlantService_validPlant_willBeDeleted();
void deletePlantService_inexistingPlant_error();
void updatePlantService_validPlant_willBeUpdatd();
void updatePlantService_inexistingPlant_error();
void nextPlantServic_notEmptyList_willReturnList();
void addPlantDisplacedListService_validPlant_willBeAdded();
void addPlantDisplacedListService_inexistentPlant_willNotBeAdded();
void nextPlantService_plantExists_willReturnNext();
void getPlantsToBeDisplacedService_notEmptyList_willReturnDisplacedList();

void runTests();