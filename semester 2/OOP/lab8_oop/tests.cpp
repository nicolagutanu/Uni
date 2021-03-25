//#include "tests.h"
//#include <assert.h>
//#include <algorithm>
//#include <cassert>
//
//
//// TEST PLANT
//void getCodedName_validCodedName_willReturnCodedName()
//{
//	Plant plant{ "I129", "Solanum", 3, "adsfnlfkjbasdlfbl" };
//	assert(plant.get_codedName() == "I129");
//}
//
//void getSpecies_validSpecies_willReturnSpecies()
//{
//	Plant plant{ "I129", "Solanum", 3, "adsfnlfkjbasdlfbl" };
//	assert(plant.get_species() == "Solanum");
//}
//
//void getAgeInMonths_validAgeInMonths_willReturnAgeInMonths()
//{
//	Plant plant{ "I129", "Solanum", 3, "adsfnlfkjbasdlfbl" };
//	assert(plant.get_ageInMonths() == 3);
//}
//
//void getDigitizedScan_validDigitizedScan_willReturnDigitizedScan()
//{
//	Plant plant{ "I129", "Solanum", 3, "adsfnlfkjbasdlfbl" };
//	assert(plant.get_digitizedScan() == "adsfnlfkjbasdlfbl");
//}
//
//void equalityOperator_notEqual_willReturnFalse()
//{
//	Plant plant1{ "I129", "Solanum", 3, "adsfnlfkjbasdlfbl" };
//	Plant plant2{ "I128", "Solan", 2, "adsfnlfbl" };
//	assert((plant1 == plant2) == false);
//}
//
//void plantToString_validPlant_willReturnPlant()
//{
//	Plant plant1{ "I129", "Solanum", 3, "adsfnlfkjbasdlfbl" };
//	assert(plant1.plantToString() == "I129 - Solanum, 3, adsfnlfkjbasdlfbl\n");
//}
//
////TEST REPOSITORY
//void addPlant_validPlant_willBeAdded()
//{
//	string path{ "E:\\School\\sem2\\OOP\\plants.txt" };
//	FileRepository repository{ path };
//	Plant plantToAdd{ "I129", "Solanum", 3, "adsfnlfkjbasdlfbl" };
//	repository.addPlant(plantToAdd);
//	assert(repository.plantExistsInList(plantToAdd) == true);
//	repository.deletePlant(plantToAdd);
//}
//
//void deletePlant_validPlant_willBeDeleted()
//{
//	string path{ "E:\\School\\sem2\\OOP\\plants.txt" };
//	FileRepository repository{ path };
//	Plant plantToDelete{ "I129", "Solanum", 3, "adsfnlfkjbasdlfbl" };
//	repository.addPlant(plantToDelete);
//	repository.deletePlant(plantToDelete);
//	assert(repository.plantExistsInList(plantToDelete) == false);
//}
//
//void updatePlant_validPlant_willBeUpdatd()
//{
//	string path{ "E:\\School\\sem2\\OOP\\plants.txt" };
//	FileRepository repository{ path };
//	Plant plant{ "I129", "Solanum", 3, "adsfnlfkjbasdlfbl" };
//	repository.addPlant(plant);
//	Plant newPlant{ "I129", "Sol", 2, "kned" };
//	repository.updatePlant(newPlant);
//	vector<Plant> plants = repository.getPlants();
//	Plant updatedPlant = plants[0];
//	assert(updatedPlant.get_codedName() == "I129");
//	assert(updatedPlant.get_species() == "Sol");
//	assert(updatedPlant.get_ageInMonths() == 2);
//	assert(updatedPlant.get_digitizedScan() == "kned");
//	repository.deletePlant(newPlant);
//}
//
//void plantExistsInList_plantExists_willReturnTrue()
//{
//	string path{ "E:\\School\\sem2\\OOP\\plants.txt" };
//	FileRepository repository{ path };
//	Plant plant{ "I129", "Solanum", 3, "adsfnlfkjbasdlfbl" };
//	repository.addPlant(plant);
//	assert(repository.plantExistsInList(plant) == true);
//	repository.deletePlant(plant);
//}
//
//void plantExistsInList_plantDoesntExists_willReturnFalse()
//{
//	string path{ "E:\\School\\sem2\\OOP\\plants.txt" };
//	FileRepository repository{ path };
//	Plant plant{ "I129", "Solanum", 3, "adsfnlfkjbasdlfbl" };
//	assert(repository.plantExistsInList(plant) == false);
//}
//
//void getPlants_notEmptyList_willReturnList()
//{
//	string path{ "E:\\School\\sem2\\OOP\\plants.txt" };
//	FileRepository repository{ path };
//	Plant plant1{ "I129", "Solanum", 3, "adsfnlfkjbasdlfbl" };
//	Plant plant2{ "I128", "Solan", 2, "adsfnlfbl" };
//	repository.addPlant(plant1);
//	repository.addPlant(plant2);
//	vector<Plant> plants = repository.getPlants();
//	assert(find(plants.begin(), plants.end(), plant1) != plants.end());
//	repository.deletePlant(plant1);
//	repository.deletePlant(plant2);
//}
//
//void addPlantToDisplacedList_validPlant_willBeAdded()
//{
//	string path{ "E:\\School\\sem2\\OOP\\plants.txt" };
//	FileRepository repository{ path };
//	Plant plant{ "I129", "Solanum", 3, "adsfnlfkjbasdlfbl" };
//	repository.addPlant(plant);
//	repository.addPlantDisplacedList(plant);
//	vector<Plant> displacedList = repository.getPlantsToBeDisplaced();
//	assert(find(displacedList.begin(), displacedList.end(), plant) != displacedList.end());
//	repository.deletePlant(plant);
//}
//
//void nextPlant_plantExists_willReturnNext()
//{
//	string path{ "E:\\School\\sem2\\OOP\\plants.txt" };
//	FileRepository repository{ path };
//	Plant plant{ "I129", "Solanum", 3, "adsfnlfkjbasdlfbl" };
//	repository.addPlant(plant);
//	Plant firstPlant = repository.nextPlant();
//	assert(firstPlant == plant);
//	repository.deletePlant(plant);
//}
//
//void plantExistsInDisplacedList_plantExists_willReturnTrue()
//{
//	string path{ "E:\\School\\sem2\\OOP\\plants.txt" };
//	FileRepository repository{ path };
//	Plant plant{ "I129", "Solanum", 3, "adsfnlfkjbasdlfbl" };
//	repository.addPlant(plant);
//	repository.addPlantDisplacedList(plant);
//	assert(repository.plantExistsInDisplacedList(plant) == true);
//	repository.deletePlant(plant);
//}
//
////TEST SERVICE
//void addPlantService_validPlant_willBeAdded()
//{
//	string path{ "E:\\School\\sem2\\OOP\\plants.txt" };
//	FileRepository repository{ path };
//	Service service{ repository };
//	assert(service.addPlantService("I129", "Solanum", 3, "adsfnlfkjbasdlfbl") == true);
//	service.deletePlantService("I129");
//}
//
//void addPlantService_existingPlant_error()
//{
//	string path{ "E:\\School\\sem2\\OOP\\plants.txt" };
//	FileRepository repository{ path };
//	Service service{ repository };
//	service.addPlantService("I129", "Solanum", 3, "adsfnlfkjbasdlfbl");
//	assert(service.addPlantService("I129", "Solanum", 3, "adsfnlfkjbasdlfbl") == false);
//	service.deletePlantService("I129");
//}
//
//void deletePlantService_validPlant_willBeDeleted()
//{
//	string path{ "E:\\School\\sem2\\OOP\\plants.txt" };
//	FileRepository repository{ path };
//	Service service{ repository };
//	service.addPlantService("I129", "Solanum", 3, "adsfnlfkjbasdlfbl");
//	assert(service.deletePlantService("I129") == true);
//}
//
//void deletePlantService_inexistingPlant_error()
//{
//	string path{ "E:\\School\\sem2\\OOP\\plants.txt" };
//	FileRepository repository{ path };
//	Service service{ repository };
//	assert(service.deletePlantService("I129") == false);
//}
//
//void updatePlantService_validPlant_willBeUpdatd()
//{
//	string path{ "E:\\School\\sem2\\OOP\\plants.txt" };
//	FileRepository repository{ path };
//	Service service{ repository };
//	service.addPlantService("I129", "Solanum", 3, "adsfnlfkjbasdlfbl");
//	assert(service.updatePlantService("I129", "Sol", 2, "jkesbfj") == true);
//	service.deletePlantService("I129");
//}
//
//void updatePlantService_inexistingPlant_error()
//{
//	string path{ "E:\\School\\sem2\\OOP\\plants.txt" };
//	FileRepository repository{ path };
//	Service service{ repository };
//	assert(service.updatePlantService("I129", "Sol", 3, "ksbfkj") == false);
//}
//
//void nextPlantServic_notEmptyList_willReturnList()
//{
//	string path{ "E:\\School\\sem2\\OOP\\plants.txt" };
//	FileRepository repository{ path };
//	Service service{ repository };
//	service.addPlantService("I129", "Solanum", 3, "bwbefj");
//	service.addPlantService("I128", "Sol", 2, "njkwnjef");
//	vector<Plant> plants = service.getPlantsService();
//	assert(find(plants.begin(), plants.end(), Plant("I129", "Solanum", 3, "bwbefj")) != plants.end());
//	service.deletePlantService("I129");
//	service.deletePlantService("I128");
//}
//
//void addPlantDisplacedListService_validPlant_willBeAdded()
//{
//	string path{ "E:\\School\\sem2\\OOP\\plants.txt" };
//	FileRepository repository{ path };
//	Service service{ repository };
//	service.addPlantService("I129", "Solanum", 3, "hsbefh");
//	assert(service.addPlantDisplacedListService("I129") == true);
//	service.deletePlantService("I129");
//}
//
//void addPlantDisplacedListService_inexistentPlant_willNotBeAdded()
//{
//	string path{ "E:\\School\\sem2\\OOP\\plants.txt" };
//	FileRepository repository{ path };
//	Service service{ repository };
//	assert(service.addPlantDisplacedListService("I129") == false);
//}
//
//void nextPlantService_plantExists_willReturnNext()
//{
//	string path{ "E:\\School\\sem2\\OOP\\plants.txt" };
//	FileRepository repository{ path };
//	Plant plant1{ "I129", "Solanum", 3, "adsfnlfkjbasdlfbl" };
//	Plant plant2{ "I128", "Solan", 2, "adsfnlfbl" };
//	repository.addPlant(plant1);
//	repository.addPlant(plant2);
//	Service service{ repository };
//	Plant nextPlant = service.nextPlantService();
//	assert(plant1 == nextPlant);
//	assert(plant1.get_codedName() == nextPlant.get_codedName());
//	assert(plant1.get_species() == nextPlant.get_species());
//	assert(plant1.get_ageInMonths() == nextPlant.get_ageInMonths());
//	assert(plant1.get_digitizedScan() == nextPlant.get_digitizedScan());
//	repository.deletePlant(plant1);
//	repository.deletePlant(plant2);
//}
//
////void getFilteredPlantsService_notEmptyList_willReturnFilteredList()
////{
////	string path{ "E:\\School\\sem2\\OOP\\plants.txt" };
////	FileRepository repository{ path };
////	Service service{ repository };
////	service.addPlantService("I129", "Solanum", 3, "bwbefj");
////	service.addPlantService("I128", "Sol", 2, "njkwnjef");
////	service.addPlantService("I127", "Sol", 1, "jkebjf");
////	service.addPlantDisplacedListService("I129");
////	service.addPlantDisplacedListService("I128");
////	service.addPlantDisplacedListService("I127");
////	vector<Plant> filteredList = service.getFilteredPlantsService("Sol", 5);
////	assert(find(filteredList.begin(), filteredList.end(), Plant("I128", "Sol", 2, "njkwnjef")) != filteredList.end());
////	service.deletePlantService("I129");
////	service.deletePlantService("I128");
////	service.deletePlantService("I127");
////}
//
//void getPlantsToBeDisplacedService_notEmptyList_willReturnDisplacedList()
//{
//	string path{ "E:\\School\\sem2\\OOP\\plants.txt" };
//	FileRepository repository{ path };
//	Service service{ repository };
//	service.addPlantService("I129", "Solanum", 3, "bwbefj");
//	service.addPlantService("I128", "Sol", 2, "njkwnjef");
//	service.addPlantDisplacedListService("I129");
//	service.addPlantDisplacedListService("I128");
//	vector<Plant> displacedList = service.getPlantsToBeDisplacedService();
//	assert(find(displacedList.begin(), displacedList.end(), Plant("I129", "Solanum", 3, "bwbefj")) != displacedList.end());
//	service.deletePlantService("I129");
//	service.deletePlantService("I128");
//}
//
//////RUN ALL TESTS
//void runTests()
//{
//	//TEST PLANT
//	getCodedName_validCodedName_willReturnCodedName();
//	getSpecies_validSpecies_willReturnSpecies();
//	getAgeInMonths_validAgeInMonths_willReturnAgeInMonths();
//	getDigitizedScan_validDigitizedScan_willReturnDigitizedScan();
//	equalityOperator_notEqual_willReturnFalse();
//	plantToString_validPlant_willReturnPlant();
//
//	//TEST REPOSITORY
//	addPlant_validPlant_willBeAdded();
//	deletePlant_validPlant_willBeDeleted();
//	updatePlant_validPlant_willBeUpdatd();
//	plantExistsInList_plantExists_willReturnTrue();
//	plantExistsInList_plantDoesntExists_willReturnFalse();
//	getPlants_notEmptyList_willReturnList();
//	addPlantToDisplacedList_validPlant_willBeAdded();
//	nextPlant_plantExists_willReturnNext();
//	plantExistsInDisplacedList_plantExists_willReturnTrue();
//
//
//	//TEST SERVICE
//	addPlantService_validPlant_willBeAdded();
//	addPlantService_existingPlant_error();
//	deletePlantService_validPlant_willBeDeleted();
//	deletePlantService_inexistingPlant_error();
//	updatePlantService_validPlant_willBeUpdatd();
//	updatePlantService_inexistingPlant_error();
//	nextPlantServic_notEmptyList_willReturnList();
//	addPlantDisplacedListService_validPlant_willBeAdded();
//	addPlantDisplacedListService_inexistentPlant_willNotBeAdded();
//	nextPlantService_plantExists_willReturnNext();
//	//getFilteredPlantsService_notEmptyList_willReturnFilteredList();
//	getPlantsToBeDisplacedService_notEmptyList_willReturnDisplacedList();
//}