#pragma warning(disable:4996)
#define _CRTDBG_MAP_ALLOC
#include "console.h"
#include <string>
#include <iostream>
#include <stdlib.h>
#include <stdio.h>
#include <crtdbg.h>
#include "validator.h"
#include <cctype>

using namespace std;

Console::Console(Service* console, Validator validator)
{
	this->console = console;
	this->validator = validator;
}

/* ADMIN
   Printing function for the admin menu */
void Console::adminMode()
{
	cout << endl;
	cout << "Admin menu:";
	cout << endl;
	cout << "Add a plant cluster" << endl;
	cout << "Delete a plant cluster" << endl;
	cout << "Update a plant cluster" << endl;
	cout << "List all plant clusters" << endl;
	cout << "Exit" << endl;
}

int Console::addPlant(const std::string& codedName, const std::string& species, const int& ageInMonths, const std::string& digitizedScan)
{
	return this->console->addPlantService(codedName, species, ageInMonths, digitizedScan);
}

int Console::deletePlant(const std::string& codedName_of_plantToDelete)
{
	return this->console->deletePlantService(codedName_of_plantToDelete);
}

int Console::updatePlant(const std::string& codedName, const std::string& newSpecies, const int& newAgeInMonths, const std::string& newDigitizedScan)
{
	return this->console->updatePlantService(codedName, newSpecies, newAgeInMonths, newDigitizedScan);
}

void Console::listPlants()
{
	std::vector<Plant> plants = this->console->getPlantsService();
	for (auto plant : plants)
	{
		cout << plant.plantToString() << '\n';
	}
}

/* Function that calls all functions used by the admin */
void Console::modeA()
{
	Console::adminMode();
	std::string mode;
	while (true)
	{
		std::string command;
		cout << ">>>" << endl;
		getline(cin, command);
		if (command == "mode B")
		{
			mode = "mode B";
			break;
		}
		char* split_command = strtok(&command[0], " ");
		if (strcmp(split_command, "add") == 0)
		{
			split_command = strtok(NULL, ", ");
			std::string codedName = split_command;
			split_command = strtok(NULL, ", ");
			std::string species = split_command;
			split_command = strtok(NULL, ", ");
			std::string auxiliary;
			auxiliary = std::string(split_command);
			validator.check_ageInMonths_IsNumber(auxiliary);
			int ageInMonths = atoi(split_command);
			split_command = strtok(NULL, ", ");
			std::string digitizedScan = split_command;
			try
			{
				this->addPlant(codedName, species, ageInMonths, digitizedScan);
			}
			catch (exception& ex)
			{
				cout << ex.what();
			}
			
		}
		else if (strcmp(split_command, "delete") == 0)
		{
			split_command = strtok(NULL, ", ");
			std::string codedName_of_plantToDelete = split_command;
			try
			{
				this->deletePlant(codedName_of_plantToDelete);
			}
			catch (exception& ex)
			{
				cout << ex.what();
			}
		}
		else if (strcmp(split_command, "update") == 0)
		{
			split_command = strtok(NULL, ", ");
			std::string codedName = split_command;
			split_command = strtok(NULL, ", ");
			std::string newSpecies = split_command;
			split_command = strtok(NULL, ", ");
			int newAgeInMonths = atoi(split_command);
			split_command = strtok(NULL, ", ");
			std::string newDigitizedScan = split_command;
			try
			{
				this->updatePlant(codedName, newSpecies, newAgeInMonths, newDigitizedScan);
			}
			catch (exception& ex)
			{
				cout << ex.what();
			}
		}
		else if (strcmp(split_command, "list") == 0)
		{
			this->listPlants();
		}
		else if (strcmp(split_command, "exit") == 0)
			exit(0);
		else
			cout << "Unexisting command, try again!" << endl;
	}
	if (mode == "mode B")
		this->modeB();
}

/* ASSISNTANT
   Printing function for the assistant menu */
void Console::assistantMode()
{
	cout << endl;
	cout << "Assistant menu:";
	cout << endl;
	cout << "Save a plant cluster to the 'to be displaced' list" << endl;
	cout << "Go through the plant cluster database with next" << endl;
	cout << "See the 'to be displaced' list" << endl;
	cout << "Filter the plant clusters by their species and their age" << endl;
	cout << "Exit" << endl;
}

int Console::addPlantToDisplacedList(const std::string& codedName)
{
	return this->console->addPlantDisplacedListService(codedName);
}

void Console::listDisplacedList()
{
	std::vector<Plant> displacedList = this->console->getPlantsToBeDisplacedService();
	for (auto plant : displacedList)
	{
		cout << plant.plantToString() << '\n';
	}
}

void Console::listBySpeciesAge(std::string& species, int ageInMonths)
{
	if (species == "")
		this->listPlants();
	else
	{
		vector<Plant> allPlants = this->console->getPlantsService();
		for (Plant plant : allPlants)
		{
			if (plant.get_species() == species && plant.get_ageInMonths() < ageInMonths)
				cout << plant.plantToString() << "\n";
		}
	}
}

void Console::next()
{
	Plant nextPlant = this->console->nextPlantService();
	cout << nextPlant.plantToString() << '\n';
}

/* Function that calls all functions used by the asisstant */
void Console::modeB()
{
	Console::assistantMode();
	std::string mode;
	while (true)
	{
		std::string command;
		cout << ">>>" << endl;
		getline(cin, command);
		if (command == "mode A")
		{
			mode = "mode A";
			break;
		}
		char* split_command = strtok(&command[0], " ");
		if (strcmp(split_command, "next") == 0)
		{
			this->next();
		}
		else if (strcmp(split_command, "mylist") == 0)
		{
			this->console->openPath();
			//this->listDisplacedList();
		}
		else if (strcmp(split_command, "list") == 0)
		{
			split_command = strtok(NULL, ", ");
			std::string species = split_command;
			split_command = strtok(NULL, ", ");
			int ageInMonths = atoi(split_command);
			this->listBySpeciesAge(species, ageInMonths);
		}
		else if (strcmp(split_command, "save") == 0)
		{
			split_command = strtok(NULL, ", ");
			std::string codedName = split_command;
			this->addPlantToDisplacedList(codedName);	
			//this->console->openPath();
		}
		else if (strcmp(split_command, "exit") == 0)
			exit(0);
		else
			cout << "Unexisting command, try again!" << endl;
	}
	if (mode == "mode A")
		this->modeA();
}

/* Fnction that runs the show */
void Console::run()
{
	while (true)
	{
		cout << "Are you an admin (mode A) or assistant (mode B)?" << endl;
		std::string mode;
		getline(cin, mode);
		if (mode == "mode A")
			this->modeA();
		else if (mode == "mode B")
			this->modeB();
		else if (mode == "exit")
			exit(0);
		else
			cout << "If you don't know what you're doing here, please exit!" << endl;
	}
}