#include "tests.h"


// TEST PLANT
void getCodedName_validCodedName_willReturnCodedName()
{
	Plant plant{ "I129", "Solanum tuberosum", 3, "adsfnlfkjbasdlfbl" };
	assert(plant.get_codedName() == "I129");
}

void getSpecies_validSpecies_willReturnSpecies()
{
	Plant plant{ "I129", "Solanum tuberosum", 3, "adsfnlfkjbasdlfbl" };
	assert(plant.get_species() == "Solanum tuberosum");
}

void getAgeInMonths_validAgeInMonths_willReturnAgeInMonths()
{
	Plant plant{ "I129", "Solanum tuberosum", 3, "adsfnlfkjbasdlfbl" };
	assert(plant.get_ageInMonths() == 3);
}

void getDigitizedScan_validDigitizedScan_willReturnDigitizedScan()
{
	Plant plant{ "I129", "Solanum tuberosum", 3, "adsfnlfkjbasdlfbl" };
	assert(plant.get_digitizedScan() == "adsfnlfkjbasdlfbl");
}

//TEST REPOSITORY
void findPlant_validPlant_willBeFound()
{
	Repository repo{};
	Plant plant{ "I129", "Solanum tuberosum", 3, "adsfnlfkjbasdlfbl" };
	assert(repo.findPlant(plant) == -1);
}

void findPlant_inexistingPlant_error()
{
	Repository repo{};
	Plant plant{ "I129", "Solanum tuberosum", 3, "adsfnlfkjbasdlfbl" };
	repo.addPlantRepo(plant);
	assert(repo.findPlant(plant) != -1);
}

void addPlant_validPlant_willBeAdded()
{
	Repository repo{};
	Plant plant{ "I129", "Solanum tuberosum", 3, "adsfnlfkjbasdlfbl" };
	assert(repo.addPlantRepo(plant) == true);
}

void addPlant_existingPlant_error()
{
	Repository repo{};
	Plant plant{ "I129", "Solanum tuberosum", 3, "adsfnlfkjbasdlfbl" };
	repo.addPlantRepo(plant);
	assert(repo.addPlantRepo(plant) == false);
}

void deletePlant_validPlant_willBeDeleted()
{
	Repository repo{};
	Plant plant{ "I129", "Solanum tuberosum", 3, "adsfnlfkjbasdlfbl" };
	repo.addPlantRepo(plant);
	assert(repo.deletePlantRepo(plant) == true);
}

void deletePlant_inexistingPlant_error()
{
	Repository repo{};
	Plant plant{ "I129", "Solanum tuberosum", 3, "adsfnlfkjbasdlfbl" };
	assert(repo.deletePlantRepo(plant) == false);
}

void updatePlant_validPlant_willBeUpdatd()
{
	Repository repo{};
	Plant plant{ "I129", "Solanum tuberosum", 3, "adsfnlfkjbasdlfbl" };
	Plant newPlant{ "I129", "Solanum tub", 3, "adsfnlf" };
	repo.addPlantRepo(plant);
	assert(repo.updatePlantRepo(newPlant, plant) == true);
}

void updatePlant_inexistingPlant_error()
{
	Repository repo{};
	Plant plant{ "I129", "Solanum tuberosum", 3, "adsfnlfkjbasdlfbl" };
	Plant newPlant{ "I129", "Solanum tub", 3, "adsfnlf" };
	assert(repo.updatePlantRepo(newPlant, plant) == false);
}

void getLength_isValid_willReturnLength()
{
	Repository repo{};
	Plant plant{ "I129", "Solanum tuberosum", 3, "adsfnlfkjbasdlfbl" };
	repo.addPlantRepo(plant);
	assert(repo.getLengthRepo() == 1);
}

void getPlantByPosition_existngPlant_willReturnPlant()
{
	Repository repo{};
	Plant plant{ "I129", "Solanum tuberosum", 3, "adsfnlfkjbasdlfbl" };
	repo.addPlantRepo(plant);
	assert(repo.getPlantByPosition(0).get_codedName() == "I129");
}

void addPlantToDisplacedList_validPlant_willBeAdded()
{
	Repository repo{};
	Plant plant{ "I129", "Solanum tuberosum", 3, "adsfnlfkjbasdlfbl" };
	repo.addPlantRepo(plant);
	assert(repo.addPlantDisplacedList(plant) == true);
}

void addPlantToDisplacedList_inexistingPlant_willNotBeAdded()
{
	Repository repo{};
	Plant plant{ "I129", "Solanum tuberosum", 3, "adsfnlfkjbasdlfbl" };
	assert(repo.addPlantDisplacedList(plant) == false);
}

void getLengthOfDisplacedList_isValid_willReturnLength()
{
	Repository repo{};
	Plant plant{ "I129", "Solanum tuberosum", 3, "adsfnlfkjbasdlfbl" };
	repo.addPlantRepo(plant);
	repo.addPlantDisplacedList(plant);
	assert(repo.getLengthOfDisplacedList() == 1);
}

//TEST SERVICE
void addPlantService_validPlant_willBeAdded()
{
	Repository repo{};
	Service service{ repo };
	assert(service.addPlantService("I129", "Solanum tuberosum", 3, "adsfnlfkjbasdlfbl") == true);
}

void addPlantService_existingPlant_error()
{
	Repository repo{};
	Service service{ repo };
	service.addPlantService("I129", "Solanum tuberosum", 3, "adsfnlfkjbasdlfbl");
	assert(service.addPlantService("I129", "Solanum tuberosum", 3, "adsfnlfkjbasdlfbl") == false);
}

void deletePlantService_validPlant_willBeDeleted()
{
	Repository repo{};
	Service service{ repo };
	service.addPlantService("I129", "Solanum tuberosum", 3, "adsfnlfkjbasdlfbl");
	assert(service.deletePlantService("I129") == true);
}

void deletePlantService_inexistingPlant_error()
{
	Repository repo{};
	Service service{ repo };
	assert(service.deletePlantService("I129") == false);
}

void updatePlantService_validPlant_willBeUpdatd()
{
	Repository repo{};
	Service service{ repo };
	service.addPlantService("I129", "Solanum tuberosum", 3, "adsfnlfkjbasdlfbl");
	assert(service.updatePlantService("I129", "Solanum t", 3, "adsfnlf") == true);
}

void updatePlantService_inexistingPlant_error()
{
	Repository repo{};
	Service service{ repo };
	assert(service.updatePlantService("I129", "Solanum t", 3, "adsfnlf") == false);
}

//RUN ALL TESTS
void runTests()
{
	//TEST PLANT
	getCodedName_validCodedName_willReturnCodedName();
	getSpecies_validSpecies_willReturnSpecies();
	getAgeInMonths_validAgeInMonths_willReturnAgeInMonths();
	getDigitizedScan_validDigitizedScan_willReturnDigitizedScan();

	//TEST REPOSITORY
	findPlant_validPlant_willBeFound();
	findPlant_inexistingPlant_error();
	addPlant_validPlant_willBeAdded();
	addPlant_existingPlant_error();
	deletePlant_validPlant_willBeDeleted();
	deletePlant_inexistingPlant_error();
	updatePlant_validPlant_willBeUpdatd();
	updatePlant_inexistingPlant_error();
	getLength_isValid_willReturnLength();
	getPlantByPosition_existngPlant_willReturnPlant();
	addPlantToDisplacedList_validPlant_willBeAdded();
	addPlantToDisplacedList_inexistingPlant_willNotBeAdded();
	getLengthOfDisplacedList_isValid_willReturnLength();

	//TEST SERVICE
	addPlantService_validPlant_willBeAdded();
	addPlantService_existingPlant_error();
	deletePlantService_validPlant_willBeDeleted();
	deletePlantService_inexistingPlant_error();
	updatePlantService_validPlant_willBeUpdatd();
	updatePlantService_inexistingPlant_error();
}