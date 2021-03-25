#pragma warning(disable:4996)
#include "console.h"
#include <string>
#include <iostream>
#include <stdlib.h>
#include <stdio.h>
#include <crtdbg.h>
#include <cctype>

using namespace std;


// ADMIN
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

void Console::addPlant(const std::string& codedName, const std::string& species, const int& ageInMonths, const std::string& digitizedScan)
{
	if (this->console.addPlantService(codedName, species, ageInMonths, digitizedScan))
		cout << "Plant added successfully!";
	else
		cout << "Plant couldn't be added!";
}

void Console::deletePlant(const std::string& codedName_of_plantToDelete)
{
	if (this->console.deletePlantService(codedName_of_plantToDelete))
		cout << "Plant deleted successfully!";
	else
		cout << "Plant couldn't be deleted!";
}

void Console::updatePlant(const std::string& codedName, const std::string& newSpecies, const int& newAgeInMonths, const std::string& newDigitizedScan)
{
	if (this->console.updatePlantService(codedName, newSpecies, newAgeInMonths, newDigitizedScan))
		cout << "Plant updated successfully!";
	else
		cout << "Plant couldn't be updated!";
}

void Console::listPlants()
{
	Plant* list_of_plants = this->console.getPlantsService();
	int length = this->console.getLengthService();
	for (int i = 0; i < length; i++)
	{
		cout << list_of_plants[i].plantToString();
	}
}

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
			split_command = strtok(NULL, ",");
			std::string codedName = split_command;
			split_command = strtok(NULL, ",");
			std::string species = split_command + 1;
			split_command = strtok(NULL, ",");
			int ageInMonths = atoi(split_command + 1);
			split_command = strtok(NULL, ",");
			std::string digitizedScan = split_command + 1;
			this->addPlant(codedName, species, ageInMonths, digitizedScan);
		}
		else if (strcmp(split_command, "delete") == 0)
		{
			split_command = strtok(NULL, ",");
			std::string codedName_of_plantToDelete = split_command;
			this->deletePlant(codedName_of_plantToDelete);
		}
		else if (strcmp(split_command, "update") == 0)
		{
			split_command = strtok(NULL, ",");
			std::string codedName = split_command;
			split_command = strtok(NULL, ",");
			std::string newSpecies = split_command + 1;
			split_command = strtok(NULL, ",");
			int newAgeInMonths = atoi(split_command + 1);
			split_command = strtok(NULL, ",");
			std::string newDigitizedScan = split_command + 1;
			this->updatePlant(codedName, newSpecies, newAgeInMonths, newDigitizedScan);
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

//ASSISTANT
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

void Console::addPlantToDisplacedList(const std::string& codedName)
{
	if (this->console.addPlantDisplacedListService(codedName))
		cout << "Plant successfully added to 'to be displaced' list!";
	else
		cout << "Plant couldn't be added to 'to be displced' list";
}

void Console::listBySpeciesAndAge(const std::string species, const int& ageInMonths)
{
	int length = this->console.getLengthService();
	Plant* fullPlantList = this->console.getPlantsService();
	for (int i = 0; i < length; i++)
		if (fullPlantList[i].get_species() == species)
			if (fullPlantList[i].get_ageInMonths() < ageInMonths)
				cout << fullPlantList[i].plantToString() << endl;
}

void Console::listDisplacedList()
{
	int length = this->console.getLengthOfDisplacedListService();
	Plant* listPlantsDisplaced = this->console.getPlantsToBeDisplacedService();
	for (int i = 0; i < length; i++)
		cout << listPlantsDisplaced[i].plantToString() << endl;
}

int Console::next(int positionInAdminList)
{
	Plant plant = this->console.getPlantByPositionService(positionInAdminList);
	cout << plant.plantToString() << endl;
	positionInAdminList += 1;
	return positionInAdminList;
}

void Console::modeB()
{
	Console::assistantMode();
	std::string mode;
	int positionInAdminList = 0;
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
			positionInAdminList = this->next(positionInAdminList);
		}
		else if (strcmp(split_command, "mylist") == 0)
		{
			this->listDisplacedList();
		}
		else if (strcmp(split_command, "list") == 0)
		{
			split_command = strtok(NULL, ",");
			std::string givenSpecies = split_command;
			split_command = strtok(NULL, ",");
			int givenAgeInMonths = atoi(split_command + 1);
			this->listBySpeciesAndAge(givenSpecies, givenAgeInMonths);
		}
		else if (strcmp(split_command, "save") == 0)
		{
			split_command = strtok(NULL, ",");
			std::string codedName = split_command;
			this->addPlantToDisplacedList(codedName);
		}
		else if (strcmp(split_command, "exit") == 0)
			exit(0);
		else
			cout << "Unexisting command, try again!" << endl;
	}
	if (mode == "mode A")
		this->modeA();
}

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