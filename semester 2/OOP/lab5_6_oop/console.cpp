#include "console.h"
#include <string>
#include <iostream>
#include <stdlib.h>
#include <stdio.h>
#include <crtdbg.h>
#include <cctype>

using namespace std;

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

void Console::assistantMode()
{
	cout << endl;
	cout << "Assistant menu:";
	cout << endl;
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

void Console::run()
{
	while (true)
	{
		cout << "Are you and admin or assistant?";
		std::string mode;
		getline(cin, mode);
		if (mode == "mode A")
		{
			Console::adminMode();
			while (true)
			{
				std::string command;
				getline(cin, command);
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
				else
					if (strcmp(split_command, "delete") == 0)
					{
						split_command = strtok(NULL, ",");
						std::string codedName_of_plantToDelete = split_command;
						this->deletePlant(codedName_of_plantToDelete);
					}
					else
						if (strcmp(split_command, "update") == 0)
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
						else
							if (strcmp(split_command, "list") == 0)
							{
								this->listPlants();
							}
							else
								if (strcmp(split_command, "exit") == 0)
								{
									exit(0);
								}
			}
		}
		else
			if (mode == "exit")
				exit(0);
			else
				cout << "Try again!";
	}
}